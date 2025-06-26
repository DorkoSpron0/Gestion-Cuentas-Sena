package com.example.app.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app.ui.screens.ActionsScreen
import com.example.app.ui.screens.CreateTransaction
import com.example.app.ui.screens.HomeScreen
import com.example.app.ui.screens.LocalWarningScreen
import com.example.app.ui.screens.TransactionDetails
import com.example.app.ui.screens.TransactionDetailsLocal
import com.example.app.ui.screens.TransactionList
import com.example.app.ui.screens.TransactionLocal
import com.example.app.ui.screens.UserLoginScreen
import com.example.app.ui.screens.UserRegisterScreen
import com.example.app.viewmodel.HomeViewModel
import com.example.app.viewmodel.TransactionLocalViewModel
import com.example.app.viewmodel.TransactionViewModel
import com.example.app.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyNavigation() {
    val viewModel: UserViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val navController = rememberNavController()
    val transactionViewModel: TransactionViewModel = hiltViewModel();
    val transactionLocalViewModel: TransactionLocalViewModel = hiltViewModel();

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home"){
            HomeScreen(
                navController
            )
        }

        composable("actions") {
            ActionsScreen(navController)
        }

        composable("register") {
            UserRegisterScreen(
                navController,
                viewModel
            )
        }

        composable("login") {
            UserLoginScreen(
                navController,
                viewModel
            )
        }

        composable("localWarning"){
            LocalWarningScreen(
                navController
            )
        }

        composable("transactions") {
            TransactionList(
                navController,
                homeViewModel,
                transactionViewModel
            )
        }

        composable("transactionsLocal") {
            TransactionLocal(
                navController,
                transactionLocalViewModel
            )
        }

        composable(
            route = "transaction/{transactionId}",
            arguments = listOf(
                navArgument("transactionId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("transactionId")
            if (id != null) {
                TransactionDetails(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    transactionViewModel = transactionViewModel,
                    transactionId = id
                )
            } else {
                println("🚨 transactionId no válido o faltante")
            }
        }

        composable(
            route = "transactionLocal/{transactionId}",
            arguments = listOf(
                navArgument("transactionId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("transactionId")
            if (id != null) {
                TransactionDetailsLocal(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    transactionLocalViewModel = transactionLocalViewModel,
                    transactionId = id
                )
            } else {
                println("🚨 transactionId no válido o faltante")
            }
        }

        composable("createTransaction") {
            CreateTransaction(
                navController,
                transactionViewModel,
                homeViewModel,
                transactionLocalViewModel
            )
        }
    }
}