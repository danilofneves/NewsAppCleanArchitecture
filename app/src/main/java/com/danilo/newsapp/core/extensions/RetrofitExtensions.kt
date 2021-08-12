package com.danilo.newsapp.core.extensions

import com.danilo.newsapp.data.exception.ApiError
import com.danilo.newsapp.data.exception.ClientErrorException
import com.danilo.newsapp.data.exception.IOErrorException
import com.danilo.newsapp.data.exception.ServerErrorException
import retrofit2.Response

fun Response<*>.getThrowable(): ApiError {
    return with(code()) {
        when {
            this in 400..499 -> ClientErrorException()
            this >= 500 -> ServerErrorException()
            else -> IOErrorException()
        }
    }
}
