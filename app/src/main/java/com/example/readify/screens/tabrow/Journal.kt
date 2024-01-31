package com.example.readify.screens.tabrow

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.readify.Loans

@Composable
fun Journal() {
    val books = listOf(
        Loans(1, 1, 1, "01/01/2024", "15/01/2024"),
        Loans(2, 1, 1, "02/01/2024", "16/01/2024"),
        Loans(3, 1, 1, "03/01/2024", "17/01/2024")
    )
    LazyColumn {
        items(books) { book ->

        }
    }
}

@Composable
fun LoanItem(){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewJournal() {
    Journal()
}