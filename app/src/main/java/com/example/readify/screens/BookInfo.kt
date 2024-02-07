package com.example.readify.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readify.Book
import com.example.readify.Client
import com.example.readify.NetworkRepository
import com.example.readify.R
import com.example.readify.RegisterVm
import com.example.readify.SharedPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookInfo(
) {
    val book = Book(
        123456,
        "01-01-2024",
        "Книга 1",
        "ООО Закрытые вопросы",
        "Автор 1",
        "Жанр 1",
        true,
        "Автобиография небезиствестного Автора 1, что прославился своими бестселлерами, такими как Книга 2 и Книга 3"
    )

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
                                /*TODO RETURN TO MAIN*/
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
                .padding(innerPadding),
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
                InfoText("Название", book.title)
                InfoText("Автор", book.author)
                InfoText("Жанр", book.genre)
                InfoText("Издание", book.publication)
                InfoText("Дата выпуска", book.realiseDate)
                InfoText("Артикул", book.article.toString())
                Text(text = "Краткое описание: ", color = MaterialTheme.colorScheme.secondary, fontSize = 20.sp)
                Text(text = book.description, fontSize = 20.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Button(onClick = {
                        val viewModel = RegisterVm(NetworkRepository(Client()))
                        viewModel.loan(
                            article = book.article,
                            id = SharedPreferences.getUserId(),
                        )
                    }) {
                        Text(text = "Забрать")
                    }
                    Button(onClick = { /*TODO*/ }) {
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