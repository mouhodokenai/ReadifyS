package com.example.readify.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readify.Client
import com.example.readify.NetworkRepository
import com.example.readify.RegisterVm

@Composable
fun Register(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }
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
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Text(text = "Регистрация", fontSize = 28.sp, fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Имя пользователя") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Эл. почта") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
        )

        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Register") },
            text = { Text("Зарегистрироваться") },
            onClick = {
                /*TODO*/
                //Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT).show()
                val viewModel = RegisterVm(NetworkRepository(Client()))
                viewModel.register(
                    name = username,
                    email = email,
                    password = password
                )
                navController.popBackStack()
                navController.navigate("login")
            }
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
    }
}