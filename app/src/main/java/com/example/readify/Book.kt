package com.example.readify

import kotlinx.serialization.*

@Serializable
data class Book(
    val article: Int,
    val realiseDate: String,
    val title: String,
    val publication: String,
    val author: String,
    val genre: String,
    val isAvailable: Boolean,
    val description: String
)

@Serializable
data class BooksListWrapper(val books: List<Book>)

@Serializable
data class Loan(
    val loanId: Int,
    val userId: Int,
    val bookTitle: String,
    val loanDate: String,
    val returnDate: String
)

@Serializable
data class LoansListWrapper(val loans: List<Loan>)

@Serializable
data class User(
    val id: Int,
    val name : String,
    val email : String,
    var password : String
)

data class Favorites(
    val userId : Int,
    val bookArticle : Int
)


