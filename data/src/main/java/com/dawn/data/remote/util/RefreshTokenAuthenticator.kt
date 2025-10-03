package com.dawn.data.remote.util

import com.dawn.common.const.SecureStorageKey
import com.tencent.mmkv.MMKV
import com.dawn.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

// TODO: Inject remote data source used to refresh token
class RefreshTokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val mmkv = MMKV.defaultMMKV()
        val refreshToken = mmkv.decodeString(SecureStorageKey.REFRESH_TOKEN) ?: return null

        return runBlocking(Dispatchers.IO) {
            val newToken = Resource.success("") //TODO: Call the remote data source to refresh the token

            if (newToken is Resource.Success) {
                val newAccessToken = "" //TODO: Get the new access token from the newToken
                val newRefreshToken = "" //TODO: Get the new refresh token from the newToken
                mmkv.encode(SecureStorageKey.ACCESS_TOKEN, newAccessToken)
                mmkv.encode(SecureStorageKey.REFRESH_TOKEN, newRefreshToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer $newAccessToken")
                    .build()
            } else {
                mmkv.removeValuesForKeys(arrayOf(SecureStorageKey.ACCESS_TOKEN, SecureStorageKey.REFRESH_TOKEN))
                null
            }
        }
    }
}