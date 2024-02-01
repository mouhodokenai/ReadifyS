package com.example.readify

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(private val network: Client) {
    suspend fun login(email: String, password: String): Boolean{
        return withContext(Dispatchers.IO){
            val request = Request("LoginRequest", mapOf(
                "email" to email,
                "password" to password
            ))

            val jsonRequest = Gson().toJson(request)
            Log.d("RequestLogging", "JSON Request: $jsonRequest")
            val serverAnswer = network.sendRequest(jsonRequest)
            val result = serverAnswer?.mapAttributes?.get("answer")
            return@withContext result == "1"
        }
    }

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
            return@withContext result == "1"
        }
    }
}