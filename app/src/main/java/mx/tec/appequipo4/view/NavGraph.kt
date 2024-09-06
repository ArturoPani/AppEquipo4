package mx.tec.appequipo4.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.tec.appequipo4.R

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("signup") {
            SignUpScreen(navController = navController)
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("catalogo") {
            Catalogo(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("producto_regulares") {
            DetalleProducto(
                navController = navController,
                titulo = "Toalla Regular",
                descripcion = "Toalla reutilizable\nIdeal para flujo moderado\nHecha a mano con telas de algodón\nMedidas: 27 x 7 cms.",
                precio = "$150.00 MXN",
                imagenId = R.drawable.image_regulares
            )
        }
        composable("producto_nocturnas") {
            DetalleProducto(
                navController = navController,
                titulo = "Toalla Nocturna",
                descripcion = "Toalla reutilizable\nIdeal para flujo abundante\nHecha a mano con telas de algodón\nMedidas: 29 x 8 cms.",
                precio = "$180.00 MXN",
                imagenId = R.drawable.image_nocturnas
            )
        }
    }
}



