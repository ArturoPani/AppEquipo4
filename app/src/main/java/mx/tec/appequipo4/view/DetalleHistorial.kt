package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

/**
 * Pantalla que muestra el detalle de un producto.
 * @param productId El ID del producto a mostrar.
 * @param viewModel El ViewModel que maneja la lógica de la aplicación.
 * @param navController El controlador de navegación de la aplicación.
 */

@Composable
fun DetalleHistorialScreen(productId: String, viewModel: UsuarioViewModel, navController: NavController) {
    val productos = viewModel.productos.observeAsState(initial = emptyList())
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val naranja = Color(0xFFE8623D)


    // Variable de estado para la cantidad seleccionada
    var cantidad by remember { mutableStateOf("1") }

    if (productos.value.isEmpty()) {
        LaunchedEffect(key1 = productId) {
            viewModel.obtenerProductosVM()
        }

        Text(text = "Cargando producto...", fontFamily = customFontPoppins)
    } else {
        val producto = productos.value.find { it.product_id.toString() == productId }
        val context = LocalContext.current
        val imageResourceId = remember(producto?.image_route) {
            producto?.image_route?.let {
                context.resources.getIdentifier(it, "drawable", context.packageName)
            } ?: 0 // Manejo de ruta nula o recurso no encontrado

        }

        val imageUrl = "http://10.0.2.2:5000/static/images/${producto?.image_route}.png"
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope() // Necesario para mostrar el Snackbar

        // Agrega SnackbarHost aquí
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (producto != null) {
                    Text(text = "Detalle del Producto", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp), fontFamily = customFontPoppins)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Nombre: ${producto.name}", fontFamily = customFontPoppins)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Descripción: ${producto.description}", fontFamily = customFontPoppins)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Precio: \$${producto.price}", fontFamily = customFontPoppins)
                    Spacer(modifier = Modifier.height(8.dp))


                    Image(//producto.image_route
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))





                    // Botón para regresar
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { navController.popBackStack() }) {
                        Text(text = "Regresar", fontFamily = customFontPoppins)
                    }
                } else {
                    Text(text = "Producto no encontrado", fontFamily = customFontPoppins)
                }


            }

            // Colocar el SnackbarHost en la parte superior de la pantalla
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        //containerColor = androidx.compose.ui.graphics.Color.Transparent, // Fondo transparent
                    )
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
