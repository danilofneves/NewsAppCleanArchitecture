package com.danilo.newsapp.data.exception

class ClientErrorException(): ApiError {
    private val msg = "Talvez você tenha um problema de conexão!"
    override fun getMessage(): String {
        return msg;
    }
}