package com.example.readify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.readify.screens.Account
import com.example.readify.screens.Auth
import com.example.readify.screens.BookInfo
import com.example.readify.screens.Register
import com.example.readify.screens.tabrow.HomeScreen
import com.example.readify.ui.theme.ReadifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPreferences.init(this)
        setContent {
            ReadifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            HomeScreen(navController, this@MainActivity)
                        }
                        composable("login"){
                            Auth(navController, this@MainActivity)
                        }
                        composable("sign up"){
                            Register(navController, this@MainActivity)
                        }
                        composable("account"){
                            Account(navController, this@MainActivity)
                        }
                        composable("bookInfo/{article}",
                            arguments = listOf(navArgument("article") { type = NavType.IntType })
                        ){backStackEntry ->
                            val argumentValue = backStackEntry.arguments?.getInt("article") ?: 1
                            BookInfo(navController,
                                this@MainActivity,
                                argumentValue
                            )
                        }
                    }
                }
            }
        }
    }
}

