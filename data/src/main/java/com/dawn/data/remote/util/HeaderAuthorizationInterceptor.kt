package com.dawn.data.remote.util

import com.tencent.mmkv.MMKV
import com.dawn.common.const.SecureStorageKey
import okhttp3.Interceptor
import okhttp3.Response

class HeaderAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mmkv = MMKV.defaultMMKV()
        val jwt = mmkv.decodeString(SecureStorageKey.ACCESS_TOKEN, "nothing")

        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $jwt")
        return chain.proceed(request.build())
    }
}