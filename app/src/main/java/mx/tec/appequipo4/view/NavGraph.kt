package mx.tec.appequipo4.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: UsuarioViewModel) {

    NavHost(navController = navController, startDestination = "main" +
            "") {

        composable("signup") {
            SignUpScreen(navController = navController)
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("catalogo") {
            CatalogoScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
            }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("conocenos") {
            ConocenosScreen(navController = navController)
        }
        composable("historial") {
            HistorialCompras(navController = navController)
        }
        composable("menu_principal") {
            MenuPrincipal(navController = navController)
        }
        composable(route = "aviso_privacidad") {
            AvisoPrivacidadScreen(navController = navController)
        }
        composable(route = "Carrito") {
            Carrito(navController = navController, viewModel = viewModel)
        }
        composable(route = "Checkout") {
            Checkout(navController = navController, viewModel = viewModel)
        }
        composable(
            route = "detalleProducto/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            // Obtener el productId de los argumentos
            val productId = backStackEntry.arguments?.getString("productId")

            // Asegúrate de que estás obteniendo el viewModel y el navController correctamente
            DetalleProductoScreen(
                productId = productId ?: "",
                viewModel = viewModel, // Si ya has inicializado el ViewModel antes
                navController = navController // Pasar el navController correctamente
            )
        }

    }
}



