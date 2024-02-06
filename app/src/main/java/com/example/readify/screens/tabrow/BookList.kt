package com.example.readify.screens.tabrow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readify.Book
import com.example.readify.BooksListWrapper
import com.example.readify.Client
import com.example.readify.MainActivity
import com.example.readify.NetworkRepository
import com.example.readify.R
import com.example.readify.RegisterVm

@Composable
fun BookList(navController: NavController, context: MainActivity) {
    val viewModel = RegisterVm(NetworkRepository(Client()))

    var books by remember {
        mutableStateOf(emptyList<Book>())
    }
    viewModel.books.observe(context){
        books = it
    }
    viewModel.show()
    LazyColumn {
        items(books) { book ->
            BookItem(book, navController)
        }
    }
}



@Composable
fun BookItem(book: Book, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .border(1.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate("bookInfo")
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.img), /*TODO PICTURES*/
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(107.dp)
                    .width(80.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = book.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "Автор: ${book.author}", color = MaterialTheme.colorScheme.secondary)
                Text(text = "Жанр: ${book.genre}", color = MaterialTheme.colorScheme.secondary)
                Text(
                    text = if (book.isAvailable) "Доступна" else "Не доступна",
                    color = if (book.isAvailable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector =
                    //if (book.isFavorite){ Icons.Outlined.FavoriteBorder}
                    Icons.Filled.Favorite,
                    contentDescription = null
                ) /*TODO FAVORITES*/
            }
            Spacer(modifier = Modifier.width(7.dp))
        }
    }
}