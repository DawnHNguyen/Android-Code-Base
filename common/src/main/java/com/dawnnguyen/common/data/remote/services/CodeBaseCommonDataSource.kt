package com.dawnnguyen.common.data.remote.services

import com.dawnnguyen.common.data.remote.dto.RefreshTokenRequest
import javax.inject.Inject

class CodeBaseCommonDataSource @Inject constructor(private val service: CodeBaseCommonService) {

    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest) = service.refreshToken(refreshTokenRequest)
}