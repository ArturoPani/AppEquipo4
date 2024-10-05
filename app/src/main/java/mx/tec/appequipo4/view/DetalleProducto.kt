package mx.tec.appequipo4.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.viewModel.UsuarioViewModel

@Composable
fun DetalleProductoScreen(productId: String, viewModel: UsuarioViewModel, navController: NavController) {
    val productos = viewModel.productos.observeAsState(initial = emptyList())

    // Variable de estado para la cantidad seleccionada
    var cantidad by remember { mutableStateOf("1") }

    if (productos.value.isEmpty()) {
        LaunchedEffect(key1 = productId) {
            viewModel.obtenerProductosVM()
        }

        Text(text = "Cargando producto...")
    } else {
        val producto = productos.value.find { it.product_id.toString() == productId }

        Column(modifier = Modifier.padding(16.dp)) {
            if (producto != null) {
                Text(text = "Detalle del Producto", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
                Text(text = "ID: ${producto.product_id}")
                Text(text = "Nombre: ${producto.name}")
                Text(text = "Descripción: ${producto.description}")
                Text(text = "Precio: \$${producto.price}")
                Text(text = "Cantidad en Stock: ${producto.stock_quality}")
                Text(text = "Categoría ID: ${producto.category_id}")
                Text(text = "Creado el: ${producto.created_at}")
                Text(text = "Imagen URL: ${producto.imageUrl}")

                Spacer(modifier = Modifier.height(16.dp))

                // Campo de texto para seleccionar la cantidad
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para agregar al carrito
                Button(onClick = {
                    // Lógica para agregar al carrito
                   // viewModel.agregarAlCarrito(producto, cantidad.toIntOrNull() ?: 1)
                }) {
                    Text(text = "Agregar al carrito")
                }

                // Botón para regresar
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Regresar")
                }
            } else {
                Text(text = "Producto no encontrado")
            }
        }
    }
}
