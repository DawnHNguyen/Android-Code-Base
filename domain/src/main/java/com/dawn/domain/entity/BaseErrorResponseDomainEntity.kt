package com.dawn.domain.entity

data  class BaseErrorResponseDomainEntity (
    val statusCode: String = "",
    val message: String = "",
    val error: String = ""
)
