package mx.tec.appequipo4.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.tec.appequipo4.viewModel.UsuarioViewModel

//import mx.tec.appequipo4.model.Product

@Composable
fun Checkout(
    navController: NavHostController,
    viewModel: UsuarioViewModel,
) {
    val productos = viewModel.carrito.observeAsState(initial = emptyList())
    val emailCompras by viewModel.emailCompras.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Checkout", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        if (productos.value.isNullOrEmpty()) {
            Text(text = "No hay productos para comprar.", fontSize = 16.sp, color = Color.Gray)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(productos.value!!) { producto ->
                    Text(text = producto.name, fontSize = 18.sp, color = Color.Black)
                    Text(text = "Precio: $${producto.price} MXN", fontSize = 14.sp, color = Color.Gray)
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
                            // Por ejemplo, limpiar el carrito y navegar a una pantalla de éxito
                            navController.navigate("menu_principal") {
                                popUpTo("menu_principal") { inclusive = true }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        Text(text = "Confirmar Compra", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}