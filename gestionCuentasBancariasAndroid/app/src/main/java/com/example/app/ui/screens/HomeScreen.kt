package com.example.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.R

@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize().padding(all = 30.dp).navigationBarsPadding().verticalScroll(
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
                id = R.drawable.creditcard
            ),
            contentDescription = "CreditCard"
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tu dinero, tus reglas!",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp)
            Text( text = "Con MisMovs, llevar el control de tus transacciones " +
                    "nunca fue tan fácil. Todo lo que necesitas para ver en qué se va tu plata, " +
                    "¡al alcance de tu dedo!",
                textAlign = TextAlign.Center)
        }

        Button(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            onClick = {
                navController.navigate("actions")
            }
        ) { Text("Continuar", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
    }
}