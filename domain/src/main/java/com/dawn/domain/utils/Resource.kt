package com.dawn.domain.utils

sealed class Resource<out T> {
    data object Idle : Resource<Nothing>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: CustomException) : Resource<T>()

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> error(error: CustomException): Resource<T> = Error(error)
        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
        fun <T> idle(): Resource<T> = Idle
    }

    // Helper properties to get data regardless of state
    val dataOrNull: T?
        get() = when (this) {
            is Success -> data
            is Error -> null
            is Loading -> data
            is Idle -> null
        }
}

// Extension functions for state checking
inline fun <T> Resource<T>.onIdle(action: () -> Unit): Resource<T> {
    if (this is Resource.Idle) action()
    return this
}

inline fun <T> Resource<T>.onLoading(action: (data: T?) -> Unit): Resource<T> {
    if (this is Resource.Loading) action(data)
    return this
}

inline fun <T> Resource<T>.onSuccess(action: (data: T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

inline fun <T> Resource<T>.onError(action: (error: CustomException) -> Unit): Resource<T> {
    if (this is Resource.Error) action(error)
    return this
}

// Data transformation extension
inline fun <X, Y> Resource<X>.map(transform: (X) -> Y): Resource<Y> = when (this) {
    is Resource.Success -> Resource.Success(transform(data))
    is Resource.Error -> Resource.Error(error)
    is Resource.Loading -> Resource.Loading(data?.let(transform))
    is Resource.Idle -> Resource.Idle
}