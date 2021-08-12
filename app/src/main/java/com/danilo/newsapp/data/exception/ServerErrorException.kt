package com.danilo.newsapp.data.exception

class ServerErrorException(): ApiError {
    private val msg = "Tivemos um problema com nosso servidor!"
    override fun getMessage(): String {
        return msg
    }
}