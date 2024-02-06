package com.example.readify

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class RegisterVm(private val repository: NetworkRepository) : ViewModel() {
    val success = MutableLiveData<Boolean>()
    val books = MutableLiveData<List<Book>>()

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            repository.register(name, email, password)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password)
        }
    }


    fun show() {
        viewModelScope.launch {
            books.value = repository.show()
        }
    }
}