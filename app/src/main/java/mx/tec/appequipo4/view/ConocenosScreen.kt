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

@Composable
fun ConocenosScreen(navController: NavController) {
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
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título "Conoce Zazil"
        Text(
            text = "CONOCE ZAZIL",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5722) // Color de fondo naranja
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección "Sobre Zazil"
        Text(
            text = "SOBRE ZAZIL",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Texto sobre Zazil
        Text(
            text = "Estamos comprometidas con el bienestar de las mujeres y el cuidado del planeta.\n" +
                    "Creamos toallas menstruales de alta calidad, hipoalergénicas y ultra absorbentes, " +
                    "diseñadas para brindarte una experiencia cómoda, segura y libre de preocupaciones durante tu ciclo.",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Nuestras toallas son reutilizables, lo que significa que puedes usarlas una y otra vez, " +
                    "reduciendo significativamente la cantidad de residuos que generas.",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen del logo de Zazil
        Image(
            painter = painterResource(id = R.drawable.img), // Reemplaza con el recurso correcto
            contentDescription = "Logo Zazil",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección "Sobre Nuestras Toallas"
        Text(
            text = "SOBRE NUESTRAS TOALLAS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Texto de características
        Text(
            text = "Todas nuestras toallas cuentan con las siguientes características:",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Características de las toallas
        Text(
            text = "• Son elaboradas a mano con telas de algodón.\n" +
                    "• Son lavables y reutilizables.\n" +
                    "• Son fáciles de limpiar y de secar.",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Logo de la fundación
        Image(
            painter = painterResource(id = R.drawable.logo_signup1), // Reemplaza con el recurso correcto
            contentDescription = "Logo Fundación",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
    }
}
