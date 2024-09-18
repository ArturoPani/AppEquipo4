
package mx.tec.appequipo4.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R

// Composable para un producto
@Composable
fun Producto(navController: NavController, nombre: String, precio: String, imagenId: Int, rutaProducto: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                // Navega a la pantalla de detalles del producto
                // Agregar cuando se implemente la pantalla de ruta de producto
                navController.navigate(rutaProducto)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = nombre,
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = nombre, fontSize = 16.sp, color = Color.Black)
        Text(text = precio, fontSize = 14.sp, color = Color.Gray)
    }
}

// Composable para mostrar el catálogo
@Composable
fun Catalogo(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() } // Volver a la página anterior
            )
            Text(text = "Catálogo", fontSize = 24.sp, color = Color.Black)
        }
        // Fila con productos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Producto(navController, "Regulares", "$150.00 MXN", R.drawable.image_regulares, "producto_regulares")
            Producto(navController, "Nocturnas", "$180.00 MXN", R.drawable.image_nocturnas, "producto_nocturnas")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Producto(navController, "Teens", "$130.00 MXN", R.drawable.image_teens, "producto_teens")
            Producto(navController, "Protector Diario", "$40.00 MXN", R.drawable.image_protector, "producto_protector")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Producto(navController, "Kit 3 piezas", "$460.00 MXN", R.drawable.image_kit3, "producto_kit3")
            Producto(navController, "Kit 5 piezas", "$540.00 MXN", R.drawable.image_kit5, "producto_kit5")
        }

        // Agrega más filas de productos según sea necesario
    }
}
