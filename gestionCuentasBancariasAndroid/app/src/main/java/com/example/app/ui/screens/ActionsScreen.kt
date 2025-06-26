package com.example.app.ui.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.R

@Composable
fun ActionsScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp).navigationBarsPadding().verticalScroll(
            rememberScrollState()
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
            Text(
                text = "MisMovs",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp)

            Image(
                modifier = Modifier.width(300.dp).height(300.dp),
                painter = painterResource(
                    id = R.drawable.actions
                ),
                contentDescription = "Actions"
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenido a MisMovs.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
                Text("Registra tus transacciones fácilmente y mantén el control de tus movimientos. " +
                        "Inicia sesión o crea tu cuenta para comenzar a organizar tus finanzas de forma simple.",
                    textAlign = TextAlign.Center)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    onClick = {
                        navController.navigate("register")
                    }
                ) { Text("Registrarse", fontSize = 16.sp, fontWeight = FontWeight.Bold) }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Button(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    onClick = {
                        navController.navigate("login")
                    }
                ) { Text("Iniciar sesión", fontSize = 16.sp, fontWeight = FontWeight.Bold) }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {navController.navigate("localWarning")},
                    ),
                    color = Color.Gray,
                    text = "Usar localmente"
                )
            }
        }

}