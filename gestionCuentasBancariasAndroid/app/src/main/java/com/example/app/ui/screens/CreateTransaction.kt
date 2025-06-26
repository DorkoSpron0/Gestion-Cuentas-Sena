package com.example.app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.R
import com.example.app.viewmodel.HomeViewModel
import com.example.app.viewmodel.TransactionLocalViewModel
import com.example.app.viewmodel.TransactionViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTransaction(navController: NavController,
                      transactionViewModel: TransactionViewModel,
                      homeViewModel: HomeViewModel,
                      transactionLocalViewModel: TransactionLocalViewModel){

    var amount by rememberSaveable { mutableLongStateOf(0L) }
    var description by rememberSaveable { mutableStateOf("") }

    val options = listOf("Ingreso", "Egreso")
    val types = listOf("Bancaria", "Efectivo", "QR", "Virtual")
    var expanded by rememberSaveable { mutableStateOf(false) }
    var expandedTypes by rememberSaveable { mutableStateOf(false) }
    var selectedOptionText by rememberSaveable { mutableStateOf(options[0]) }
    var selectedTypesText by rememberSaveable { mutableStateOf(types[0]) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                title = { Text("Agregar transacción", textAlign = TextAlign.Center) },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 30.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Image(
                        modifier = Modifier.width(300.dp).height(300.dp),
                        painter = painterResource(
                            id = R.drawable.avatar
                        ),
                        contentDescription = "Avatar Male"
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Cantidad") },
                        singleLine = true,
                        value = amount.toString(),
                        onValueChange = { input ->
                            amount = input.toLongOrNull() ?: 0L
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Descripción") },
                        value = description,
                        onValueChange = {input -> description = input},
                    )

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedOptionText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Tipo de transacción") },
                            trailingIcon = {
                                Icon(if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                    "icon",
                                    Modifier.clickable { expanded = !expanded })
                            },
                            modifier = Modifier
                                .menuAnchor() // Importante para que el menú se posicione bien
                                .fillMaxWidth()
                        )

                        // Menú desplegable
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }


                    ExposedDropdownMenuBox(
                        expanded = expandedTypes,
                        onExpandedChange = { expandedTypes = !expandedTypes }
                    ) {
                        OutlinedTextField(
                            value = selectedTypesText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Tipo de cuenta") },
                            trailingIcon = {
                                Icon(if(expandedTypes) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                    "icon",
                                    Modifier.clickable { expandedTypes = !expandedTypes })
                            },
                            modifier = Modifier
                                .menuAnchor() // Importante para que el menú se posicione bien
                                .fillMaxWidth()
                        )

                        // Menú desplegable
                        ExposedDropdownMenu(
                            expanded = expandedTypes,
                            onDismissRequest = { expandedTypes = false }
                        ) {
                            types.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedTypesText = selectionOption
                                        expandedTypes = false
                                    }
                                )
                            }
                        }
                    }

                    Button(
                        enabled = amount > 0 && description.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if(homeViewModel.getUserId() != null){
                                transactionViewModel.createTransaction(
                                    homeViewModel.getUserId()!!,
                                    amount,
                                    description,
                                    selectedOptionText,
                                    selectedTypesText
                                )

                                navController.navigate("transactions")
                            }else{
                                transactionLocalViewModel.createTransaction(
                                    amount,
                                    description,
                                    selectedOptionText,
                                    selectedTypesText
                                )

                                navController.navigate("transactionsLocal")
                            }

                        }
                    ) { Text("Guardar") }

                }
            }
        }
    }
}