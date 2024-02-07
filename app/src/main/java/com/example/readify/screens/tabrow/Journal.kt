package com.example.readify.screens.tabrow

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.readify.Book
import com.example.readify.Client
import com.example.readify.MainActivity
import com.example.readify.NetworkRepository
import com.example.readify.RegisterVm
import com.example.readify.SharedPreferences
import java.time.LocalTime

@Composable
fun Journal(navController: NavController, context: MainActivity) {

    val viewModel = RegisterVm(NetworkRepository(Client()))

    var books by remember {
        mutableStateOf(emptyList<Book>())
    }
    viewModel.books.observe(context){
        books = it
    }
    viewModel.showBooks()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (SharedPreferences.containsKey()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .border(1.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = getGreeting() + "user", fontSize = 15.sp) /*TODO*/
                        Text(text = "Количество взятых книг: n") /*TODO*/
                    }
                    IconButton(onClick = {
                        navController.navigate("account")
                    }) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
                    }
                }
                /*TODO FAVORITES*/
                LazyColumn {
                    items(books) { book ->
                        BookItem(book, navController)
                    }
                }
            }
        } else {
            Button(onClick = {
                navController.popBackStack()
                navController.navigate("login")
            }) {
                Text(text = "Вход")
            }
        }
    }
}

fun getGreeting(): String {
    return when (LocalTime.now()) {
        in LocalTime.of(6, 0)..LocalTime.of(11, 59) -> "Доброе утро, "
        in LocalTime.of(12, 0)..LocalTime.of(17, 59) -> "Добрый день, "
        in LocalTime.of(18, 0)..LocalTime.of(23, 59) -> "Добрый вечер, "
        else -> "Доброй ночи, "
    }
}