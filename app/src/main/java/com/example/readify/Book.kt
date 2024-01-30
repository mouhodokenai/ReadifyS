package com.example.readify

data class Book (
    val article : Int,
    val realiseDate: String,
    val title: String,
    val publication : String,
    val author: String,
    val genre: String,
    val isAvailable: Boolean,
    val description : String
)

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

data class Loans(
    val id: Int,
    val userId: Int,
    val bookId : Int,
    val loanDate : String,
    val returnDate : String
)