package com.example.readify

import kotlinx.serialization.Serializable

@Serializable
data class BooksListWrapper(val books: List<Book>)
