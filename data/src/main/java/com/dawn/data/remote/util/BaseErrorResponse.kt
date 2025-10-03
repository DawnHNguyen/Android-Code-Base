package com.dawn.data.remote.util

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("statusCode")
    val statusCode: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("error")
    val error: String?
)