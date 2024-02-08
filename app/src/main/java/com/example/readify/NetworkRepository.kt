package com.example.readify

import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(private val network: Client) {

    suspend fun register(name: String, email: String, password: String): Boolean {
        return withContext(Dispatchers.IO){
            val request = Request("RegisterRequest", mapOf(
                "name" to name,
                "email" to email,
                "password" to password
            ))

            val jsonRequest = Gson().toJson(request)
            Log.d("RequestLogging", "JSON Request: $jsonRequest")
            val serverAnswer = network.sendRequest(jsonRequest)
            val result = serverAnswer?.mapAttributes?.get("answer")
            Log.d("AnswerLogging", "Результат $serverAnswer")
            println(result)
            return@withContext result != null
        }
    }

    suspend fun showLoans() : List<Loan>{ //
        return withContext(Dispatchers.IO) {
            val request = Request("ShowLoans", mapOf("name" to "name"))

            val jsonRequest = Gson().toJson(request)
            Log.d("книжка", "JSON Request: $jsonRequest")
            return@withContext network.sendLoanRequest(jsonRequest)
        }
    }

    suspend fun show() : List<Book>{
        return withContext(Dispatchers.IO) {
            val request = Request("ShowBooks", mapOf("name" to "name"))

            val jsonRequest = Gson().toJson(request)
            Log.d("книжка", "JSON Request: $jsonRequest")
            return@withContext network.sendBookRequest(jsonRequest)
        }
    }

    suspend fun takeABook(article: Int, id: Int): Boolean{
        return withContext(Dispatchers.IO){
            val request = RequestI("TakeABook", mapOf(
                "article" to article,
                "id" to id
            )
            )

            val jsonRequest = Gson().toJson(request)
            Log.d("RequestLogging", "JSON Request: $jsonRequest")
            val serverAnswer = network.sendRequest(jsonRequest)
            val result = serverAnswer?.mapAttributes?.get("answer")
            Log.d("AnswerLogging", "Результат $serverAnswer")
            println(result)
            return@withContext result == "1"
        }
    }
    suspend fun returnABook(article: String) : Book{
        return withContext(Dispatchers.IO) {
            val request = Request("ShowBook", mapOf("id" to article))

            val jsonRequest = Gson().toJson(request)
            Log.d("книжка", "JSON Request: $jsonRequest")
            return@withContext network.sendABookRequest(jsonRequest)
        }
    }
    suspend fun login(email: String, password: String): User{
        return withContext(Dispatchers.IO){
            val request = Request("LoginRequest", mapOf(
                "email" to email,
                "password" to password
            ))

            val jsonRequest = Gson().toJson(request)
            Log.d("RequestLogging", "JSON Request: $jsonRequest")

            return@withContext network.sendAUserRequest(jsonRequest);
        }
    }
}