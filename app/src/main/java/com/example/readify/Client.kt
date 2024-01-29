package com.example.readify

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

class Client() {
    val answer = MutableLiveData<Map<*, *>>()
    private suspend fun connect(): io.ktor.network.sockets.Socket {
        return aSocket(
            ActorSelectorManager(
                Executors.newCachedThreadPool()
                    .asCoroutineDispatcher()
            )
        )
            .tcp()
            .connect(io.ktor.network.sockets.InetSocketAddress("192.168.0.103", 8006))
    }

    suspend fun sendRequest(text: String): Answer? {
        val socket = connect()
        socket.openWriteChannel(autoFlush = true).writeStringUtf8(text)
        val reader = socket.openReadChannel()
        val answer = reader.readUTF8Line().toString()

        return Gson().fromJson(answer, Answer::class.java)

    }
}