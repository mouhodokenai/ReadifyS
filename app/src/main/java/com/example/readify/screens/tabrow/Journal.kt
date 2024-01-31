package com.example.readify.screens.tabrow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readify.Loans
import com.example.readify.SharedPreferences
import java.time.LocalTime

@Composable
fun Journal(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (SharedPreferences.containsKey()){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                contentAlignment = Alignment.Center){
                Text(text = getGreeting() + "user", fontSize = 15.sp) /*TODO*/
            }
        } else {
            Button(onClick = {
                navController.popBackStack()
                navController.navigate("login")
            }) {
                Text(text = "Log in")
            }
        }
    }


    /*
    val books = listOf(
        Loans(1, 1, 1, "01/01/2024", "15/01/2024"),
        Loans(2, 1, 1, "02/01/2024", "16/01/2024"),
        Loans(3, 1, 1, "03/01/2024", "17/01/2024")
    )
    Column{
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            contentAlignment = Alignment.Center){
            Text(text = getGreeting() + "user", fontSize = 15.sp) /*TODO*/
        }
        /*TODO LOANS*/
        LazyColumn {
            items(books) { book ->
                LoanItem(book)
            }
        }
    }

     */
}

@Composable
fun LoanItem(book: Loans) {

}

fun getGreeting(): String {
    return when (LocalTime.now()) {
        in LocalTime.of(6, 0)..LocalTime.of(11, 59) -> "Доброе утро, "
        in LocalTime.of(12, 0)..LocalTime.of(17, 59) -> "Добрый день, "
        in LocalTime.of(18, 0)..LocalTime.of(23, 59) -> "Добрый вечер, "
        else -> "Доброй ночи, "
    }
}