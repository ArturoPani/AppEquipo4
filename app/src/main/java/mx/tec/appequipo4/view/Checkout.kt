package mx.tec.appequipo4.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

//import mx.tec.appequipo4.model.Product

/**
 * Pantalla que muestra el resumen del carrito de compras y permite confirmar la compra.
 * @param navController Controlador de navegación para navegar entre pantallas.
 * @param viewModel Modelo de vista asociado a la pantalla.
 */

@Composable
fun Checkout(
    navController: NavHostController,
    viewModel: UsuarioViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val naranja = Color(0xFFE8623D)
    val productos = viewModel.carrito.observeAsState(initial = emptyList())
    val emailCompras by viewModel.emailCompras.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Checkout", fontSize = 20.sp, color = Color.Black, fontFamily = customFont)

        Spacer(modifier = Modifier.height(16.dp))

        if (productos.value.isNullOrEmpty()) {
            Text(text = "No hay productos para comprar.", fontSize = 16.sp, color = Color.Gray, fontFamily = customFontPoppins)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(productos.value!!) { producto ->
                    Text(text = producto.name, fontSize = 18.sp, color = Color.Black, fontFamily = customFontPoppins)
                    Text(text = "Precio: $${producto.price} MXN", fontSize = 14.sp, color = Color.Gray, fontFamily = customFontPoppins)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para confirmar compra
                    Button(
                        onClick = {
                            // Lógica para confirmar la compra
                            viewModel.hacerCheckoutVM(emailCompras)
                            println("Compra confirmada en checkout.kt")
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Compra confirmada, información enviada al correo electrónico",
                                    duration = SnackbarDuration.Short
                                )
                            }
                            // Por ejemplo, limpiar el carrito y navegar a una pantalla de éxito
                            navController.navigate("menu_principal") {
                                popUpTo("menu_principal") { inclusive = true }
                            }
                        },
                        //modifier = Modifier.fillMaxWidth().background(customColor),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(text = "Confirmar Compra", fontSize = 16.sp, fontFamily = customFontPoppins)
                    }
                }
            }
        }
    }
}