package com.danilo.newsapp.data.exception

class ResponseError(val apiError: ApiError){
    fun getMessage():String = apiError.getMessage()
}