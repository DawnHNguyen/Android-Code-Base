package com.dawnnguyen.common.domain.repository

import com.dawnnguyen.common.data.remote.util.Resource

interface CommonRepository {
    suspend fun refreshToken(): Resource<String>
}