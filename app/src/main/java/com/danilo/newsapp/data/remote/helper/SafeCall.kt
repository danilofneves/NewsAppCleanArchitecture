package com.danilo.newsapp.data.remote.helper

import com.danilo.newsapp.core.extensions.getThrowable
import com.danilo.newsapp.data.exception.IOErrorException
import com.danilo.newsapp.data.exception.ResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend fun safeCall(call: suspend () -> Response<*>): Any? {
    return withContext(Dispatchers.IO) {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()
            } else {
                ResponseError(response.getThrowable())
            }
        }
        catch (e:IOException){
            ResponseError(IOErrorException())
        }
    }
}