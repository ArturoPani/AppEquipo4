package mx.tec.appequipo4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

/**
 * Pantalla que muestra el catálogo de productos.
 * @param navController Controlador de navegación para navegar entre pantallas.
 * @param viewModel ViewModel asociado al catálogo.
 */

@Composable
fun HistorialScreen(navController: NavController, viewModel: UsuarioViewModel) {
    // Observamos los productos del ViewModel
    val productos = viewModel.productosHistorial.observeAsState(initial = emptyList())
    val emailCompras by viewModel.emailCompras.collectAsState()
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val amarilloClaro = Color(0xFF39D402)
    val naranja = Color(0xFFE8623D)


    // Estructura principal
    Column(modifier = Modifier.padding(16.dp)) {
        // Fila (Row) con la flecha de regreso y el título del catálogo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // Flecha de regresar
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, // Usa el ícono por defecto de Jetpack Compose
                    contentDescription = "Regresar"
                )
            }

            // Título de la pantalla de catálogo
            Text(
                text = "Historial de Compras",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
        }

        // Mostramos los productos en una LazyColumn
        LazyColumn {
            items(productos.value) { producto ->
                ProductoHistorialComposable(producto) {
                    // Navegar a la pantalla de detalle del producto con su ID
                    navController.navigate("detalleProducto/${producto.product_id}")
                }
            }
        }
        viewModel.obtenerProductosHistorialVM(emailCompras)
    }


}
