package com.example.readify

data class Book (
    val article : Int, /*1 user: M book*/
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
    var password : String,
    var favoriteBook: Int
)