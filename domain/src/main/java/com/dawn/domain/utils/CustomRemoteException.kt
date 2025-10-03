package com.dawn.domain.utils

import com.dawn.domain.entity.BaseErrorResponseDomainEntity

//4xx
class NoNetworkException(
    error: BaseErrorResponseDomainEntity?,
    override val message: String?,
    requestUrl: String
) : CustomException(error, requestUrl, StatusCodeCategory.CLIENT_ERROR)

class NetworkAuthenticationException(error: BaseErrorResponseDomainEntity?, requestUrl: String) :
    CustomException(error, requestUrl, StatusCodeCategory.CLIENT_ERROR)

class NetworkForbiddenException(error: BaseErrorResponseDomainEntity?, requestUrl: String) :
    CustomException(error, requestUrl, StatusCodeCategory.CLIENT_ERROR)

class NetworkResourceNotFoundException(error: BaseErrorResponseDomainEntity?, requestUrl: String) :
    CustomException(error, requestUrl, StatusCodeCategory.CLIENT_ERROR)

class RequestTimeoutException(error: BaseErrorResponseDomainEntity?, requestUrl: String) :
    CustomException(error, requestUrl, StatusCodeCategory.CLIENT_ERROR)

class BadRequestException(error: BaseErrorResponseDomainEntity?, requestUrl: String) :
    CustomException(error, requestUrl, StatusCodeCategory.CLIENT_ERROR)

class UnknownException(
    error: BaseErrorResponseDomainEntity?,
    override val message: String?,
    requestUrl: String
) : CustomException(error, requestUrl, StatusCodeCategory.CLIENT_ERROR)

// 5xx
class NetworkServerException(error: BaseErrorResponseDomainEntity?, requestUrl: String) :
    CustomException(error, requestUrl, StatusCodeCategory.SERVER_ERROR)

class NetworkException(
    error: BaseErrorResponseDomainEntity?,
    override val message: String?,
    requestUrl: String
) : CustomException(error, requestUrl, StatusCodeCategory.SERVER_ERROR)

sealed class CustomException(
    val error: BaseErrorResponseDomainEntity?,
    val requestUrl: String,
    val statusCodeCategory: StatusCodeCategory
) : Exception()

enum class StatusCodeCategory {
    CLIENT_ERROR, SERVER_ERROR
}