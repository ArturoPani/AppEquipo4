package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
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

@Composable
fun DetalleProducto(
    navController: NavController,
    titulo: String,
    descripcion: String,
    precio: String,
    imagenId: Int,
    cantidad: Int = 1
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Barra de navegación superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón para regresar
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() } // Volver a la página anterior
            )

            // Título del producto
            Text(text = titulo, fontSize = 24.sp, color = Color.Black)

            // Botón para cerrar
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Cerrar",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen del producto
        Image(
            painter = painterResource(id = imagenId), // Imagen del producto
            contentDescription = titulo,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Detalles del producto
        Text(text = titulo, fontSize = 20.sp, color = Color.Black)
        Text(text = descripcion, fontSize = 14.sp, color = Color.Gray)
        Text(text = precio, fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        // Control para cantidad
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { /* Disminuir cantidad */ }) {
                Text(text = "-")
            }

            Text(text = cantidad.toString(), fontSize = 20.sp)

            Button(onClick = { /* Aumentar cantidad */ }) {
                Text(text = "+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de agregar al carrito
        Button(
            onClick = { /* Acción de agregar al carrito */ },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Agregar al carrito", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
