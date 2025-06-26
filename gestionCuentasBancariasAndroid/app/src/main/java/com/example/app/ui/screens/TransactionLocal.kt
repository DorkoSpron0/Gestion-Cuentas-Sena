package com.example.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.viewmodel.TransactionLocalViewModel

@Composable
fun TransactionLocal(
    navController: NavController,
    viewModel: TransactionLocalViewModel
){
    val transactions by viewModel.transactionsLocal.collectAsState();
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(transactions.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No tienes aún ninguna transacción")
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            navController.navigate("createTransaction")
                        }
                    ) { Text("Crear una transacción") }
                }
            }else{

                Text(
                    text = "Tus transacciones",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate("createTransaction")
                    }
                ) { Text("Crear una trasacción") }

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                LazyColumn {
                    items(transactions.size) {
                        val tr = transactions[it]
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(15.dp))
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        navController.navigate("transactionLocal/${tr.id}")                                    }
                                )
                                .background(if (tr.amountType == "EXPENSE") Color.Red.copy(alpha = 0.08f) else Color.Green.copy(alpha = 0.08f))
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(tr.type, fontWeight = FontWeight.Bold)
                                }
                                Text(
                                    text = tr.amount.toString() + " $",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(tr.description)
                                Text(
                                    text = tr.dateTime!!.substring(0, 10),
                                    color = Color.Gray
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                    }
                }
            }
        }
    }
}