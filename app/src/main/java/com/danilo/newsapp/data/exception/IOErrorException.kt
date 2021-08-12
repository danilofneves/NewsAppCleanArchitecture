package com.danilo.newsapp.data.exception

class IOErrorException(): ApiError {
    private val msg = "Sem conexão!"   //In seconds
    override fun getMessage(): String {
        return msg
    }
}