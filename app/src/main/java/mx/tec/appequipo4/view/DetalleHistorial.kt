package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun DetalleHistorial(
    navController: NavController,
    fecha: String,
    nombreProducto: String,
    cantidad: Int,
    precio: String,
    imagenId: Int,
    instruccionesDevolucion: String,
    rutaCompra: String
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón de inicio
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() } // Volver a la página anterior
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = "Historial de compras",
            fontSize = 28.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen del producto
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = nombreProducto,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Información del producto
        Text(text = "Pedido realizado el $fecha", fontSize = 16.sp, color = Color.Black)
        Text(text = nombreProducto, fontSize = 20.sp, color = Color.Black)
        Text(text = "Cantidad: $cantidad", fontSize = 16.sp, color = Color.Gray)
        Text(text = precio, fontSize = 18.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        // Instrucciones de devolución
        Text(
            text = instruccionesDevolucion,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para comprar nuevamente
        Button(
            onClick = { navController.navigate(rutaCompra) },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = "Comprar nuevamente", fontSize = 16.sp)
        }
    }
}
