package com.example.readify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterVm(private val repository: NetworkRepository): ViewModel() {
    val success = MutableLiveData<Boolean>()
    fun register(name: String, email: String, password: String){
        viewModelScope.launch {
            repository.register(name, email, password)
        }
    }
    fun login(email: String, password: String){
        viewModelScope.launch {
            repository.login(email, password)
        }
    }
}