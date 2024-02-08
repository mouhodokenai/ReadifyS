package com.example.readify

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.asCoroutineDispatcher

import java.util.concurrent.Executors
import kotlinx.serialization.*

import com.google.gson.Gson

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
            .connect(io.ktor.network.sockets.InetSocketAddress("192.168.43.100", 8006))
    }

    suspend fun sendRequest(text: String): Answer? {
        val socket = connect()
        socket.openWriteChannel(autoFlush = true).writeStringUtf8(text)

        // Логирование перед получением ответа
        Log.d("RequestLogging", "Sent request: $text")

        val reader = socket.openReadChannel()
        val answer = reader.readUTF8Line().toString()

        // Логирование после получения ответа
        Log.d("AnswerLogging", "Raw Server Response: $answer")

        try {
            // Попытка десериализации ответа
            val parsedAnswer = Gson().fromJson(answer, Answer::class.java)
            Log.d("AnswerLogging", "Parsed Answer: ${parsedAnswer.mapAttributes["answer"]}")

            return parsedAnswer
        } catch (e: Exception) {
            // Обработка ошибок десериализации и логирование
            e.printStackTrace()
            Log.e("AnswerLogging", "Error parsing JSON response: $answer")
            return null
        }
    }

    suspend fun sendLoanRequest(text: String): List<Loan> {
        val socket = connect()
        socket.openWriteChannel(autoFlush = true).writeStringUtf8(text)

        // Логирование перед получением ответа
        Log.d("RequestLogging", "Sent request: $text")

        val reader = socket.openReadChannel()
        val answer = reader.readUTF8Line().toString()

        // Логирование после получения ответа
        Log.d("AnswerLogging", "Raw Server Response: $answer")

        try {
            // Попытка десериализации ответа с использованием kotlinx.serialization
            val loansList = Json.decodeFromString<LoansListWrapper>(answer).loans
            Log.d("AnswerLogging", "Parsed Answer: $loansList")

            return loansList
        } catch (e: Exception) {
            // Обработка ошибок десериализации и логирование
            e.printStackTrace()
            Log.e("AnswerLogging", "Error parsing JSON response: $answer")
            return emptyList<Loan>()
        }
    }
    suspend fun sendBookRequest(text: String): List<Book> {
        val socket = connect()
        socket.openWriteChannel(autoFlush = true).writeStringUtf8(text)

        // Логирование перед получением ответа
        Log.d("RequestLogging", "Sent request: $text")

        val reader = socket.openReadChannel()
        val answer = reader.readUTF8Line().toString()

        // Логирование после получения ответа
        Log.d("AnswerLogging", "Raw Server Response: $answer")

        try {
            // Попытка десериализации ответа с использованием kotlinx.serialization
            val booksList = Json.decodeFromString<BooksListWrapper>(answer).books
            Log.d("AnswerLogging", "Parsed Answer: $booksList")

            return booksList
        } catch (e: Exception) {
            // Обработка ошибок десериализации и логирование
            e.printStackTrace()
            Log.e("AnswerLogging", "Error parsing JSON response: $answer")
            return emptyList<Book>()
        }
    }
    suspend fun sendABookRequest(text: String): Book {
        val socket = connect()
        socket.openWriteChannel(autoFlush = true).writeStringUtf8(text)

        // Логирование перед получением ответа
        Log.d("RequestLogging", "Sent request: $text")

        val reader = socket.openReadChannel()
        val answer = reader.readUTF8Line().toString()

        // Логирование после получения ответа
        Log.d("AnswerLogging", "Raw Server Response: $answer")

        try {
            // Попытка десериализации ответа с использованием kotlinx.serialization
            val book = Gson().fromJson(answer, Book::class.java)
            Log.e("BookLogging", "Book: $book")
            return book
        } catch (e: Exception) {
            // Обработка ошибок десериализации и логирование
            e.printStackTrace()
            Log.e("AnswerLogging", "Error parsing JSON response: $answer")
            return Book(0, "", "", "", "", "", true, "")
        }
    }

    suspend fun sendAUserRequest(text: String): User {
        val socket = connect()
        socket.openWriteChannel(autoFlush = true).writeStringUtf8(text)

        // Логирование перед получением ответа
        Log.d("RequestLogging", "Sent request: $text")

        val reader = socket.openReadChannel()
        val answer = reader.readUTF8Line().toString()

        // Логирование после получения ответа
        Log.d("AnswerLogging", "Raw Server Response: $answer")

        try {
            // Попытка десериализации ответа с использованием kotlinx.serialization
            val user = Gson().fromJson(answer, User::class.java)
            Log.e("UserLogging", "User: $user")
            return user
        } catch (e: Exception) {
            // Обработка ошибок десериализации и логирование
            e.printStackTrace()
            Log.e("AnswerLogging", "Error parsing JSON response: $answer")
            return User(0, "", "", "")
        }
    }
}