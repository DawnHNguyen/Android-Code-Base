package com.dawn.data.remote.util

import com.dawn.data.mapping.toDomainEntity
import com.dawn.domain.utils.BadRequestException
import com.dawn.domain.utils.NetworkAuthenticationException
import com.dawn.domain.utils.NetworkException
import com.dawn.domain.utils.NetworkForbiddenException
import com.dawn.domain.utils.NetworkResourceNotFoundException
import com.dawn.domain.utils.NetworkServerException
import com.dawn.domain.utils.NoNetworkException
import com.dawn.domain.utils.RequestTimeoutException
import com.dawn.domain.utils.Resource
import com.dawn.domain.utils.UnknownException
import com.google.gson.Gson
import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.net.ssl.SSLHandshakeException

class CallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = CallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        check(returnType is ParameterizedType) {
            throw IllegalStateException(
                "Response return type must be parameterized as Call<Resource<Foo>> or Call<Resource<out Foo>>"
            )
        }

        val responseType = getParameterUpperBound(0, returnType)

        if (getRawType(responseType) != Resource::class.java) {
            return null
        }

        check(responseType is ParameterizedType) {
            "Response must be parameterized as Resource<Foo> or Resource<out Foo>"
        }

        val successBodyType = getParameterUpperBound(0, responseType)

        val converter =
            retrofit.nextResponseBodyConverter<Resource<Any>>(null, successBodyType, annotations)

        return BodyCallAdapter(successBodyType, converter)
    }

    private class BodyCallAdapter<T : Any>(
        private val responseType: Type,
        private val converter: Converter<ResponseBody, Resource<T>>,
    ) : CallAdapter<T, Call<Resource<T>>> {

        override fun responseType(): Type = responseType

        override fun adapt(call: Call<T>): Call<Resource<T>> {
            return ResourceCall(call, converter)
        }
    }

    internal class ResourceCall<S : Any>(
        private val delegate: Call<S>,
        private val converter: Converter<ResponseBody, Resource<S>>,
    ) :
        Call<Resource<S>> {
        override fun enqueue(callback: Callback<Resource<S>>) {

            delegate.enqueue(object : Callback<S> {
                override fun onFailure(call: Call<S>, t: Throwable) {
                    val apiResponse: Resource<Nothing> = when (t) {
                        is SSLHandshakeException, is IOException -> Resource.error(
                            NoNetworkException(
                                null,
                                "No network connection",
                                "${call.request().method} ${call.request().url}"
                            ),
                        )
                        //SSLHandshakeException is thrown when user's internet connection is disconnected
                        //before the server can return response
                        else -> {
                            Resource.error(
                                UnknownException(
                                    null,
                                    t.toString(),
                                    "${call.request().method} ${call.request().url}"
                                )
                            )
                        }
                    }
                    callback.onResponse(this@ResourceCall, Response.success(apiResponse))
                }

                override fun onResponse(call: Call<S>, response: Response<S>) {
                    val body = response.body()
                    val code = response.code()
                    val errorBody = response.errorBody()?.string().orEmpty()

                    if (response.isSuccessful && (response.body() != null || code == 204)) {
                        callback.onResponse(
                            this@ResourceCall, Response.success(
                                Resource.success(
                                    body!!
                                )
                            )
                        )
                    } else {
                        val gson = Gson()
                        val error: BaseErrorResponse? = try {
                            gson.fromJson(errorBody, BaseErrorResponse::class.java)
                        } catch (e: Exception) {
                            null
                        }

                        val domainError = error?.toDomainEntity()

                        val exception = when (code) {
                            400 -> BadRequestException(
                                domainError,
                                "${call.request().method} ${call.request().url}"
                            )

                            401 -> NetworkAuthenticationException(
                                domainError,
                                "${call.request().method} ${call.request().url}"
                            )

                            403 -> NetworkForbiddenException(
                                domainError,
                                "${call.request().method} ${call.request().url}"
                            )

                            404 -> NetworkResourceNotFoundException(
                                domainError,
                                "${call.request().method} ${call.request().url}"
                            )

                            408 -> RequestTimeoutException(
                                domainError,
                                "${call.request().method} ${call.request().url}"
                            )

                            500 -> NetworkServerException(
                                domainError,
                                "${call.request().method} ${call.request().url}"
                            )

                            in 400..499 -> UnknownException(
                                domainError,
                                "Unknown client error with status code $code errorBody = $errorBody",
                                "${call.request().method} ${call.request().url}"
                            )

                            else -> NetworkException(
                                domainError,
                                "Unknown server error with status code $code errorBody = $errorBody",
                                "${call.request().method} ${call.request().url}"
                            )
                        }

                        callback.onResponse(
                            this@ResourceCall, Response.success(
                                Resource.error(exception)
                            )
                        )
                    }
                }
            })
        }

        override fun isExecuted(): Boolean = delegate.isExecuted

        override fun timeout(): Timeout {
            return delegate.timeout()
        }

        override fun clone(): Call<Resource<S>> = ResourceCall(delegate.clone(), converter)

        override fun isCanceled(): Boolean = delegate.isCanceled

        override fun cancel() = delegate.cancel()

        override fun execute(): Response<Resource<S>> {
            throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
        }

        override fun request(): Request = delegate.request()
    }
}