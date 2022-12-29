package com.dawnnguyen.common.data.repository

import com.orhanobut.hawk.Hawk
import com.dawnnguyen.common.constpackage.HawkKey
import com.dawnnguyen.common.data.remote.dto.RefreshTokenRequest
import com.dawnnguyen.common.data.remote.services.CodeBaseCommonDataSource
import com.dawnnguyen.common.data.remote.util.Resource
import com.dawnnguyen.common.domain.repository.CommonRepository
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(private val dataSource: CodeBaseCommonDataSource):
    CommonRepository {

    override suspend fun refreshToken(): Resource<String> {
        val refreshToken = Hawk.get(HawkKey.REFRESH_TOKEN, "null")
        val refreshTokenRequest = RefreshTokenRequest(refreshToken)
        val refreshTokenResponse = dataSource.refreshToken(refreshTokenRequest)
        if (refreshTokenResponse.isSuccessful()) Hawk.put(HawkKey.ACCESS_TOKEN, refreshTokenResponse.data)
        return refreshTokenResponse
    }
}