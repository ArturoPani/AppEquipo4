package mx.tec.appequipo4.view



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.tec.appequipo4.R
import mx.tec.appequipo4.model.Product
//import mx.tec.appequipo4.model.Product
import mx.tec.appequipo4.viewModel.UsuarioViewModel

@Composable
fun Carrito(
    navController: NavHostController,
    viewModel: UsuarioViewModel // Pasamos el ViewModel para manejar la eliminación
) {
    val productos = viewModel.carrito.observeAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Carrito de Compras", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        if (productos.value.isNullOrEmpty()) {
            Text(text = "Tu carrito está vacío.", fontSize = 16.sp, color = Color.Gray)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(productos.value!!) { producto ->
                    ProductoItem(
                        producto = producto,
                        navController = navController,
                        eliminarProductoDelCarrito = { viewModel.eliminarProductoDelCarrito(producto) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    //val total = productos.sumOf { it.price }
                    //Text(text = "Total: $$total MXN", fontSize = 20.sp, color = Color.Black)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón de Checkout
                    Button(
                        onClick = { navController.navigate("checkout") },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(text = "Realizar Compra", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoItem(
    producto: Product,
    navController: NavHostController,
    eliminarProductoDelCarrito: (Product) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto
            val context = LocalContext.current
            val imageResourceId = remember(producto.image_route) {
                context.resources.getIdentifier(producto.image_route, "drawable", context.packageName)
            }
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = producto.name,
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Gray, shape = CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = producto.name, fontSize = 18.sp, color = Color.Black)
                Text(text = "Precio: $${producto.price} MXN", fontSize = 14.sp, color = Color.Gray)
                Text(text = "Cantidad: 1", fontSize = 14.sp, color = Color.Gray) // Asumiendo una cantidad por producto
            }

            // Botón para eliminar del carrito
            Icon(
                imageVector = Icons.Default.Close, // Asegúrate de tener un ícono de cerrar en drawable
                contentDescription = "Eliminar",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        eliminarProductoDelCarrito(producto)
                    },
                tint = Color.Red
            )
        }
    }
}