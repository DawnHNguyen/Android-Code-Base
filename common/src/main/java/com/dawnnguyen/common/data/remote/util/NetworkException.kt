package com.dawnnguyen.common.data.remote.util

class NoNetworkException(error: Error?, message: String?) : CustomException(error)
class NetworkAuthenticationException(error: Error?) : CustomException(error)
class NetworkServerException(error: Error?) : CustomException(error)
class NetworkResourceNotFoundException(error: Error?) : CustomException(error)
class RequestTimeoutException(error: Error?) : CustomException(error)
class BadRequestException(error: Error?) : CustomException(error)
class UnknownException(error: Error?, message: String?) : CustomException(error)
class NetworkException(error: Error?) : CustomException(error)
sealed class CustomException(val error: Error?) : Exception()