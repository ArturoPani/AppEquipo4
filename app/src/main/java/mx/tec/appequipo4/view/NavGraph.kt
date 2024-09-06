package mx.tec.appequipo4.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = "signup") {
    NavHost(navController = navController, startDestination = "Login") {
        composable("signup") {
            SignUpScreen(navController = navController)
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
    }
}



