package com.dawn.data.mapping

import com.dawn.domain.entity.BaseErrorResponseDomainEntity
import com.dawn.data.remote.util.BaseErrorResponse

fun BaseErrorResponse.toDomainEntity(): BaseErrorResponseDomainEntity {
    return BaseErrorResponseDomainEntity(
        statusCode = this.statusCode ?: "",
        message = this.message ?: "",
        error = this.error ?: ""
    )
}