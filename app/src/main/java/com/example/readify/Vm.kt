package com.example.readify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Vm(private val repository: NetworkRepository) : ViewModel() {
    val success = MutableLiveData<Boolean>()
    val books = MutableLiveData<List<Book>>()
    val loans = MutableLiveData<List<Loan>>()
    val selectedBook = MutableLiveData<Book>()
    val user = MutableLiveData<User>()

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            repository.register(name, email, password)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            user.value = repository.login(email, password)
        }
    }

    fun showBooks() {
        viewModelScope.launch {
            books.value = repository.show()
        }
    }

    fun showLoans() {
        viewModelScope.launch {
            loans.value = repository.showLoans()
        }
    }

    fun loan(article: Int, id: Int) {
        viewModelScope.launch {
            repository.takeABook(article, id)
        }
    }

    fun book(article: String) {
        viewModelScope.launch {
            selectedBook.value = repository.returnABook(article)
        }
    }
}