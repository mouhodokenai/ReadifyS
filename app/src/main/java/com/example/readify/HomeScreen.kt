package com.example.readify

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    val tabList = listOf(
        TabItem(
            "Home",
            Icons.Outlined.Home,
            Icons.Filled.Home
        ),
        TabItem(
            "Search",
            Icons.Outlined.Search,
            Icons.Filled.Search
        ),
        TabItem(
            "Favorites",
            Icons.Outlined.FavoriteBorder,
            Icons.Filled.Favorite
        ),
    )
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabList.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabList.forEachIndexed { index, item ->
                Tab(selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector =
                            if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BookList()
                //Text(text = tabList[index].title)
            }
        }
    }
}
@Composable
fun BookList() {
    val books = listOf(
        BookInfo("Книга 1", "Автор 1", "Жанр 1", true),
        BookInfo("Книга 2", "Автор 2", "Жанр 2", false),
        BookInfo("Книга 2", "Автор 2", "Жанр 2", false),
        BookInfo("Книга 2", "Автор 2", "Жанр 2", false),
        BookInfo("Книга 2", "Автор 2", "Жанр 2", false),
        BookInfo("Книга 2", "Автор 2", "Жанр 2", false),
        BookInfo("Книга 2", "Автор 2", "Жанр 2", false),
        BookInfo("Книга 2", "Автор 2", "Жанр 2", false),
    )
    LazyColumn {
        items(books) { book ->
            BookItem(book)
        }
    }
}
data class BookInfo(
    val title: String,
    val author: String,
    val genre: String,
    val isAvailable: Boolean

)
@Composable
fun BookItem(book: BookInfo) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.Magenta)
            .padding(16.dp)
            .border(1.dp, MaterialTheme.colorScheme.secondary, shape = MaterialTheme.shapes.medium)
            .clickable { }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
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

        }
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

