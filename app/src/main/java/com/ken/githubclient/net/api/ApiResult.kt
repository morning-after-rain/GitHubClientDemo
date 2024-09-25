package com.ken.githubclient.net.api

import androidx.annotation.Keep


@Keep
data class ApiResult<T>(
    val errorCode: Int,
    val errorMsg: String,
    private val data: T?
) {
    fun apiData(): T {
        if (errorCode == 0 && data != null) {
            return data
        } else {
            throw ApiException(errorCode, errorMsg)
        }
    }
}

class ApiException(var code: Int, override var message: String) : RuntimeException()
