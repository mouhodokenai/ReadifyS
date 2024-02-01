package com.example.readify.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.readify.Client
import com.example.readify.NetworkRepository
import com.example.readify.RegisterVm
import com.example.readify.SharedPreferences
import java.math.BigInteger
import java.security.MessageDigest

@Composable
fun Auth(navController: NavController) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
        )


        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Check, contentDescription = "Log in") },
                text = { Text("Log in") },
                onClick = {
                    /*TODO*/
                    val viewModel = RegisterVm(NetworkRepository(Client()))
                    viewModel.login(
                        email = email,
                        password = password
                    )
                    SharedPreferences.setUserId(1)
                    navController.popBackStack()
                    navController.navigate("home")
                }
            )
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
                text = { Text("Register") },
                onClick = {
                    navController.popBackStack()
                    navController.navigate("sign up")
                }
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
    }
}


fun hash(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}