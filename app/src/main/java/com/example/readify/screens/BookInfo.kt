package com.example.readify.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readify.Book
import com.example.readify.Client
import com.example.readify.MainActivity
import com.example.readify.NetworkRepository
import com.example.readify.R
import com.example.readify.Vm
import com.example.readify.SharedPreferences
import com.example.readify.screens.tabrow.addFavorites

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookInfo(
    navController: NavController,
    context: MainActivity,
    argumentValue: Int
) {

    var isFavorite by remember {
        mutableStateOf(false)
    }
    val userId = SharedPreferences.getUserId()
    val viewModel = remember { Vm(NetworkRepository(Client())) }
    val bookState by viewModel.selectedBook.observeAsState(
        initial = Book(
            1,
            " ",
            " ",
            " ",
            " ",
            " ",
            true,
            " "
        )
    )

    val scroll = rememberScrollState()

    LaunchedEffect(argumentValue) {
        viewModel.book(argumentValue.toString())
    }

    Scaffold(
        topBar = {
            Row {
                TopAppBar(
                    title = { /*TODO*/ },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                                navController.navigate("home")
                            }
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.img), /*TODO PICTURES*/
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(400.dp)
                    .width(292.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                InfoText("Название", bookState.title)
                InfoText("Автор", bookState.author)
                InfoText("Жанр", bookState.genre)
                InfoText("Издание", bookState.publication)
                InfoText("Дата выпуска", bookState.realiseDate)
                InfoText("Артикул", bookState.article.toString())
                Text(
                    text = "Краткое описание: ",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 20.sp
                )
                Text(text = bookState.description, fontSize = 20.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        if (bookState.isAvailable) {
                            bookState.isAvailable = false
                            viewModel.loan(
                                article = bookState.article,
                                id = SharedPreferences.getUserId()
                            )
                        } else {
                            Toast.makeText(context, "Книга занята", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text(text = "Забрать")
                    }
                    Button(onClick = {
                        if (SharedPreferences.containsKey() && !isFavorite) {
                            isFavorite = true
                            addFavorites( bookState.article, SharedPreferences.getUserId())
                        } else {
                            Toast.makeText(context, "Книга уже в избранных", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text(text = "В избранное")
                    }
                }
            }
        }
    }
}


@Composable
fun InfoText(text: String, value: String) {
    Row {
        Text(text = "${text}: ", color = MaterialTheme.colorScheme.secondary, fontSize = 20.sp)
        Text(text = value, fontSize = 20.sp)
    }
}