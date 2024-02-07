package com.example.readify.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readify.Client
import com.example.readify.Loan
import com.example.readify.MainActivity
import com.example.readify.NetworkRepository
import com.example.readify.R
import com.example.readify.RegisterVm
import com.example.readify.User
import com.example.readify.screens.tabrow.getGreeting
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@Composable
fun Account(navController: NavController, context: MainActivity) {
    val user = User(1, "Anastasia", "prayfortherain@yandex.ru", "123456")

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        Text(text = getGreeting() + user.name, fontSize = 15.sp) /*TODO*/
        Text(text = "Ваша эл. почта: ${user.email}") /*TODO*/

        val viewModel = RegisterVm(NetworkRepository(Client()))

        var books by remember {
            mutableStateOf(emptyList<Loan>())
        }
        viewModel.loans.observe(context){
            books = it
        }
        viewModel.showLoans()

        LazyColumn {
            items(books) { book ->
                LoanItem(book)
            }
        }

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Выйти из аккаунта")
        }
    }
}

@Composable
fun LoanItem(book : Loan) {
    var color = if (compareDate(book.returnDate)) {
        Color.Black
    } else {
        Color.Gray
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .border(1.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp))
            .clickable {
                /*  TODO    */
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

                Text(text = book.bookTitle, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = color)
                Text(text = "Дата получения книги: ${book.loanDate}", color = color)
                Text(text = "Дата возврата: ${book.returnDate}", color = color)
            }
        }
    }
}

fun getToday(): String {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return today.format(formatter)
}

fun compareDate(date: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date1 = LocalDate.parse(date, formatter)
    val date2 = LocalDate.parse(getToday(), formatter)

    val comparisonResult = date1.compareTo(date2)

    return when {
        comparisonResult < 0 -> false
        comparisonResult > 0 -> true
        else -> true
    }
}

fun addMonth(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currentDate = Calendar.getInstance()

    currentDate.time = dateFormat.parse(getToday())!!

    currentDate.add(Calendar.MONTH, 1)

    val newDate = currentDate.time

    return dateFormat.format(newDate)
}
