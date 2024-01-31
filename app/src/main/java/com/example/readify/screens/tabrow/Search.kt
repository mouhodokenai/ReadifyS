package com.example.readify.screens.tabrow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.readify.Book

@Composable
fun Search(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val bookList = remember { getMockBookList() }
    var expanded by remember { mutableStateOf(false) }
    var selectedGenre by remember { mutableStateOf("") }
    /*val genres = listOf(
        "Драма",
        "Комедия",
        "Трагедия",
        "Реалистическая проза",
        "Детектив",
        "Приключенческая литература",
        "Любовный роман",
        "Исторический роман",
        "Фантастика и фэнтези",
        "Сказки",
        "Научная литература"
    ) */
    val genres = listOf("Fiction", "Dystopian", "Romance")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue: String ->
                    searchQuery = newValue
                    selectedGenre = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                    selectedGenre = ""
                },
                modifier = Modifier
                    .defaultMinSize(50.dp, 50.dp)
            ) {
                genres.forEach { genre ->
                    DropdownMenuItem(
                        text = { Text(text = genre) },
                        onClick = {
                            selectedGenre = genre
                            expanded = !expanded
                        }
                    )
                }
            }
        }
        LazyColumn {
            items(bookList.filter {
                (searchQuery != "" && it.title.lowercase()
                    .contains(searchQuery.lowercase())) || it.genre == selectedGenre
            }
            ) { book ->
                BookItem(book)
            }
        }
    }
}

fun getMockBookList(): List<Book> {
    return listOf(
        Book(
            1,
            "2022-01-01",
            "The Great Gatsby",
            "Publisher A",
            "F. Scott Fitzgerald",
            "Fiction",
            true,
            "A classic novel"
        ),
        Book(
            2,
            "2022-02-15",
            "To Kill a Mockingbird",
            "Publisher B",
            "Harper Lee",
            "Fiction",
            true,
            "A powerful story of justice"
        ),
        Book(
            3,
            "2022-03-10",
            "1984",
            "Publisher C",
            "George Orwell",
            "Dystopian",
            true,
            "A dystopian classic"
        ),
        Book(
            4,
            "2022-04-25",
            "Pride and Prejudice",
            "Publisher D",
            "Jane Austen",
            "Romance",
            true,
            "A classic love story"
        )
    )
}