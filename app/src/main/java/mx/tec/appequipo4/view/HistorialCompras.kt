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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

/**
 * Pantalla que muestra el historial de compras del usuario
 */

// Composable para cada bloque de historial de compras
@Composable
fun HistorialItem(
    navController: NavController,
    fecha: String,
    nombreProducto: String,
    cantidad: Int,
    imagenId: Int,
    rutaDetalles: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen del producto
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = nombreProducto,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Información del producto
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Pedido realizado el $fecha", fontSize = 16.sp, color = Color.Black)
            Text(text = nombreProducto, fontSize = 14.sp, color = Color.Gray)
            Text(text = "Cantidad: $cantidad", fontSize = 14.sp, color = Color.Gray)
            Text(
                text = "Más detalles",
                fontSize = 14.sp,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate(rutaDetalles)
                }
            )
        }
    }
}

// Pantalla principal del historial de compras
@Composable
fun HistorialCompras(navController: NavController,viewModel: UsuarioViewModel) {
    val scrollState = rememberScrollState()
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Volver",
        modifier = Modifier
            .size(32.dp)
            .clickable { navController.popBackStack() } // Volver a la página anterior
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Título de la página
        Text(
            text = "Historial de compras",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Elementos de historial
        HistorialItem(
            navController = navController,
            fecha = "21 de junio",
            nombreProducto = "Kit 6 piezas",
            cantidad = 1,
            imagenId = R.drawable.image_kit3,
            rutaDetalles = "detalle_kit6"
        )

        HistorialItem(
            navController = navController,
            fecha = "21 de junio",
            nombreProducto = "Toalla Teens",
            cantidad = 4,
            imagenId = R.drawable.image_teens,
            rutaDetalles = "detalles_teens"
        )

        HistorialItem(
            navController = navController,
            fecha = "25 de mayo",
            nombreProducto = "Kit 3 piezas",
            cantidad = 1,
            imagenId = R.drawable.image_kit3,
            rutaDetalles = "detalles_kit3"
        )

        HistorialItem(
            navController = navController,
            fecha = "9 de abril",
            nombreProducto = "Toalla Nocturna",
            cantidad = 4,
            imagenId = R.drawable.image_nocturnas,
            rutaDetalles = "detalles_nocturnas"
        )

        // Puedes agregar más elementos aquí usando el mismo composable `HistorialItem`
    }
}
