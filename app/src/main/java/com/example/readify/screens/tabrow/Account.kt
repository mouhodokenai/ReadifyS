package com.example.readify.screens.tabrow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.readify.Loans

@Composable
fun Account() {
    val books = listOf(
        Loans(1, 1, 1, "01/01/2024", "15/01/2024"),
        Loans(2, 1, 1, "02/01/2024", "16/01/2024"),
        Loans(3, 1, 1, "03/01/2024", "17/01/2024")
    )
    LazyColumn {
        items(books) { book ->
            BookCard(
                book,
                onExtendClick = { /* Handle extend click */ },
                onReturnClick = { /* Handle return click */ }
            )
        }
    }
}

@Composable
fun BookCard(book: Loans, onExtendClick: () -> Unit, onReturnClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Автор: -", style = MaterialTheme.typography.bodyLarge)
                Icon(Icons.Default.Person, contentDescription = null)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Одолжено: ${book.loanDate}", style = MaterialTheme.typography.bodyMedium)
                Icon(Icons.Default.DateRange, contentDescription = null)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Возврат до: ${book.returnDate}", style = MaterialTheme.typography.bodyMedium)
                Icon(Icons.Default.DateRange, contentDescription = null)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onExtendClick) {
                    Icon(Icons.Default.Refresh, contentDescription = null)
                }
                IconButton(onClick = onReturnClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAccount() {
    Account()
}