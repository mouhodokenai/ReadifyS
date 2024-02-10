package com.example.readify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Vm(private val repository: NetworkRepository) : ViewModel() {
    val success = MutableLiveData<Boolean>()
    val books = MutableLiveData<List<Book>>()
    val favs = MutableLiveData<List<Book>>()
    val loans = MutableLiveData<List<Loan>>()
    val selectedBook = MutableLiveData<Book>()
    val user = MutableLiveData<User>()

    fun book(article: String) {
        viewModelScope.launch {
            val book = repository.returnABook(article)
            selectedBook.postValue(book)
        }
    }


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

    fun showFavs(id: String) {
        viewModelScope.launch {
            favs.value = repository.showFavs(id)
        }
    }

    fun makeFavs(article: String, id: String) {
        viewModelScope.launch {
            repository.makeFav(article, id)
        }
    }

    fun showLoans(id: Int) {
        viewModelScope.launch {
            loans.value = repository.showLoans(id)
        }
    }

    fun loan(article: Int, id: Int) {
        viewModelScope.launch {
            repository.takeABook(article, id)
        }
    }
}