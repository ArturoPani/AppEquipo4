package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
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
        val context = LocalContext.current
        val imageResourceId = remember(producto?.image_route) {
            producto?.image_route?.let {
                context.resources.getIdentifier(it, "drawable", context.packageName)
            } ?: 0 // Manejo de ruta nula o recurso no encontrado

        }
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope() // Necesario para mostrar el Snackbar
        Column(modifier = Modifier.padding(16.dp)) {
            if (producto != null) {
                Text(text = "Detalle del Producto", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
                Text(text = "Nombre: ${producto.name}")
                Text(text = "Descripción: ${producto.description}")
                Text(text = "Precio: \$${producto.price}")
                Text(text = "Cantidad en Stock: ${producto.stock_quality}")
                Text(text = "Categoría ID: ${producto.category_id}")
                Text(text = "Creado el: ${producto.created_at}")

                Image(
                    painter = painterResource(id = imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )
                //Text(text = "Imagen URL: ${producto.image_route}")

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

                Button(onClick = {
                    // Lógica para agregar al carrito
                    viewModel.agregarProductoAlCarrito(producto)

                    // Mostrar el Snackbar
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Producto agregado al carrito",
                            duration = SnackbarDuration.Long
                        )
                    }
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


            // Botón flotante para ir al carrito
            FloatingActionButton(
                onClick = { navController.navigate("Carrito") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Ir al carrito"
                )
            }
        }
    }
}
