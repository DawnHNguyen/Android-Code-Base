package com.dawnnguyen.common.domain.usecase

import com.dawnnguyen.common.domain.repository.CommonRepository
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(private val repository: CommonRepository) {
    suspend operator fun invoke() = repository.refreshToken()
}