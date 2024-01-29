package com.example.readify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.readify.ui.theme.ReadifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            HomeScreen(navController)
                        }
                        composable("bookInfo/{article}",
                            //arguments = listOf(navArgument("article") { type = NavType.IntType })
                        ){//backStackEntry ->
                            //val argumentValue = backStackEntry.arguments?.getInt("article") ?: 1
                            BookInfo(
                                //argumentValue
                            )
                        }
                    }
                }
            }
        }
    }
}

