package com.example.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.R
import com.example.app.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterScreen(navController: NavController, viewModel: UserViewModel){
    val userResponse by viewModel.user.collectAsState()
    val error by viewModel.error.collectAsState()


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(userResponse) {
        userResponse?.let { response ->
            if (response.isSuccessful) {
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
            }
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                title = { Text("Registrarse", textAlign = TextAlign.Center) },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(30.dp).navigationBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Image(
                    modifier = Modifier.width(250.dp).height(250.dp),
                    painter = painterResource(
                        id = R.drawable.avatar
                    ),
                    contentDescription = "Avatar Male"
                )

                Spacer(
                    modifier = Modifier.height(40.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") },
                    singleLine = true,
                    value = email,
                    onValueChange = {input -> email = input}
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    value = password,
                    onValueChange = {input -> password = input}
                )

                Spacer(
                    modifier = Modifier.height(40.dp)
                )

                Button(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    enabled = email.isNotBlank() && password.isNotBlank(),
                    onClick = {
                        viewModel.registerUser(email, password)
                    }
                ) { Text("Register") }

                error?.let {
                    Text(text = it, color = Color.Red)
                }
            }
        }
    }
}