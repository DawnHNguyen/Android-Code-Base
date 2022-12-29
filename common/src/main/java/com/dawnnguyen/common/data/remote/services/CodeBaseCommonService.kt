package com.dawnnguyen.common.data.remote.services

import com.dawnnguyen.common.data.remote.dto.RefreshTokenRequest
import com.dawnnguyen.common.data.remote.util.Resource
import retrofit2.http.Body
import retrofit2.http.POST

interface CodeBaseCommonService {

    @POST("auth/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Resource<String>
}