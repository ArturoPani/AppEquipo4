package mx.tec.appequipo4.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.tec.appequipo4.R

@Composable
fun NavGraph(navController: NavHostController) {

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
        composable("detalle_kit6") {
            DetalleHistorial(
                navController = navController,
                fecha = "21 de junio",
                nombreProducto = "Kit 6 piezas Ayni",
                cantidad = 1,
                precio = "$580.00 MXN",
                imagenId = R.drawable.image_kit5,
                instruccionesDevolucion = "Para devolver el producto contacta al número 5512345678. No todos los productos son elegibles para devolución.",
                rutaCompra = "producto_regulares"
            )
        }
    }
}



