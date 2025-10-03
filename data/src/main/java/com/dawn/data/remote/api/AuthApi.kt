package com.dawn.data.remote.api

import com.dawn.data.remote.dto.LoginRequest
import com.dawn.data.remote.dto.LoginResponse
import com.dawn.domain.utils.Resource
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Resource<LoginResponse>
}
