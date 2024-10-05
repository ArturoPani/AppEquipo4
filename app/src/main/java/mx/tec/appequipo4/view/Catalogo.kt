package mx.tec.appequipo4.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.tec.appequipo4.R // Asegúrate de que el ícono esté en tu carpeta de recursos
import mx.tec.appequipo4.viewModel.UsuarioViewModel


@Composable
fun CatalogoScreen(navController: NavController, viewModel: UsuarioViewModel = viewModel()) {
    // Observamos los productos del ViewModel
    val productos = viewModel.productos.observeAsState(initial = emptyList())

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
                text = "Catálogo",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
        }

        // Mostramos los productos en una LazyColumn
        LazyColumn {
            items(productos.value) { producto ->
                ProductoComposable(product = producto) {
                    // Navegar a la pantalla de detalle del producto con su ID
                    navController.navigate("detalleProducto/${producto.product_id}")
                }
            }
        }
    }

    // Cargamos los productos desde el backend
    viewModel.obtenerProductosVM()
}
