package com.example.readify

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Network(private val network: Client) {
    suspend fun login(login: String, password: String){
        return withContext(Dispatchers.IO){
            val request = Request("login", mapOf())
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