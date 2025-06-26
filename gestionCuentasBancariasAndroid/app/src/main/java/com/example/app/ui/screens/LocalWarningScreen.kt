package com.example.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalWarningScreen(navController: NavController){

    var checked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                title = { Text("Usar localmente", textAlign = TextAlign.Center) },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver") }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
                Text("""
                Esta aplicación permite trabajar tanto con almacenamiento local como con datos vinculados a la nube. Es fundamental que comprendas cómo funciona esta diferencia para evitar la pérdida de información y mantener un control adecuado sobre tus transacciones.
    
                Cuando utilizas la aplicación en modo local, toda la información que registres se guarda únicamente en el dispositivo en el que estás trabajando. Esto significa que los datos no se subirán ni se sincronizarán automáticamente con la nube. Tampoco estarán disponibles desde otros dispositivos ni podrán recuperarse en caso de que elimines la aplicación o borres los datos desde la configuración del sistema. Si decides borrar o desinstalar la app, toda la información local se perderá de forma permanente, sin posibilidad de recuperación.
    
                En caso de que accedas con una cuenta vinculada a la nube, los datos que registres bajo esa cuenta sí serán almacenados en línea. Adicionalmente, cada vez que ingreses con tu cuenta y guardes información en la nube, esos datos también se almacenarán de forma local en tu dispositivo para que puedas acceder a ellos sin necesidad de conexión a internet. No obstante, es importante tener claro que esto no implica una sincronización constante o automática entre lo que está en la nube y lo que está en tu dispositivo. Los cambios que realices de forma local no se reflejarán automáticamente en la nube, y lo que modifiques en la nube desde otro dispositivo no se actualizará por sí solo en este dispositivo.
    
                En resumen, la aplicación puede usar datos locales y datos en la nube, pero no mantiene una sincronización bidireccional automática. Por eso, te recomendamos tener presente en todo momento en qué modo estás trabajando y asegurarte de utilizar el almacenamiento en la nube si deseas conservar tu información a largo plazo o acceder a ella desde varios dispositivos.
            """.trimIndent())
            Spacer(
                modifier = Modifier.height(40.dp)
            )

                Row {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )
                    Text("Declaro que he leído y comprendido las condiciones sobre el uso de almacenamiento local y en la nube, y acepto continuar bajo esos términos.")
                }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

                Button(
                    enabled = checked,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate("transactionsLocal")
                    }
                ) { Text("Continuar", fontSize = 16.sp, fontWeight = FontWeight.Bold) }
            }
        }
}