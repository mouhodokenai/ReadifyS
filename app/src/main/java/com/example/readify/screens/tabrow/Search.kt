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
import com.example.readify.Client
import com.example.readify.MainActivity
import com.example.readify.NetworkRepository
import com.example.readify.Vm

@Composable
fun Search(navController: NavController, context: MainActivity) {
    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedGenre by remember { mutableStateOf("") }


    val viewModel = Vm(NetworkRepository(Client()))

    var bookList by remember {
        mutableStateOf(emptyList<Book>())
    }
    viewModel.books.observe(context) {
        bookList = it
    }
    viewModel.showBooks()

    val genres = listOf(
        "Драма",
        "Фэнтези",
        "Роман",
        "Сказка",
        "Драма",
        "Детектив",
        "Ужасы",
        "Наука",
        "Рассказ",
        "Эротика",
        "Манга",
        "Пособие",
        "Комедия",
        "Философия",
        "Антиутопия",
        "Фантастика",
        "Приключения",
        "Реализм"
    )

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
                BookItem(book, navController)
            }
        }
    }
}