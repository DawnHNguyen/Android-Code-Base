package com.dawnnguyen.common.data.remote.util

import com.orhanobut.hawk.Hawk
import com.dawnnguyen.common.constpackage.HawkKey
import okhttp3.Interceptor
import okhttp3.Response

class HeaderAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val jwt = Hawk.get<String>(HawkKey.ACCESS_TOKEN)
        val headers = request.headers.newBuilder().add("authorization", jwt ?: "null").build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)
    }
}
