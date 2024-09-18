package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R

@Composable
fun MenuPrincipal(navController: NavController) {
    // Fondo degradado
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFA07A), // Color más claro
            Color(0xFFFF6347)  // Color más oscuro
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp), // Ajustar espacio en la parte inferior
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen grande en la parte superior
            Image(
                painter = painterResource(id = R.drawable.img), // Reemplaza con tu recurso de imagen
                contentDescription = "Imagen principal",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp),
                contentScale = ContentScale.Fit
            )

            // Botón para navegar al Catálogo
            AppButton(
                text = "Catálogo",
                backgroundColor = Color(0xFF2196F3),
                onClick = { navController.navigate("catalogo") }
            )

            // Botón para navegar al Historial de Compras
            AppButton(
                text = "Historial de Compras",
                backgroundColor = Color(0xFF4CAF50),
                onClick = { navController.navigate("historial") }
            )

            // Botón para navegar a Conócenos
            AppButton(
                text = "Conócenos",
                backgroundColor = Color(0xFFFF5722),
                onClick = { navController.navigate("conocenos") }
            )

            // Botón "Regresar" para volver a la página principal
            AppButton(
                text = "Regresar",
                backgroundColor = Color(0xFF9C27B0), // Color púrpura, por ejemplo
                onClick = { navController.navigate("main") }
            )

            Spacer(modifier = Modifier.height(32.dp)) // Agregar más espacio entre botones y el aviso
        }

        // Texto de "Aviso de privacidad" en la parte inferior derecha
        Text(
            text = "Aviso de privacidad",
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            textAlign = TextAlign.Right
        )
    }
}
