package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R

@Composable
fun ConocenosScreen(navController: NavController) {
    val backgroundColor = Color(0xFFFEE0D7)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ajustar el ícono de regreso para que se vea correctamente
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,  // Cambiar a Icons.Filled.ArrowBack
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() } // Volver a la página anterior
            )

            AppTitle()
        }
        Image(
            painter = painterResource(id = R.drawable.img_1), // Imagen de la presidenta
            contentDescription = "Virydiana Fernández",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "\"Juntos, iluminaremos el camino hacia una generación libre, informada y empoderada.\" \n ¡Libertad para Todas! \n\n Virydiana Fernández \n Presidenta Fundación Todas Brillamos AC",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección "¿Quiénes Somos?" en una sola columna
        Text(
            text = "¿QUIÉNES SOMOS?",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_2), // Imagen del producto
            contentDescription = "Producto Zazil",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Zazil es una marca comprometida con el bienestar de las mujeres y el cuidado del medio ambiente. Su misión es proporcionar soluciones innovadoras y sostenibles para el período menstrual. ¿Cómo lo hacen? A través de la creación de toallas femeninas reutilizables.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "\"Cambiando el Mundo, un Ciclo a la Vez.\"",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB22222), // Color rojo
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección Misión y Visión en una sola columna
        Text(
            text = "NUESTRA MISIÓN",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_3), // Imagen de la misión
            contentDescription = "Nuestra Misión",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "En Zazil, no solo estamos redefiniendo la menstruación, sino también el impacto que tiene en la economía y el medio ambiente. Nuestra misión es empoderar a las mujeres a tomar decisiones informadas sobre su salud menstrual mientras generan un impacto positivo en su bienestar financiero y en el planeta.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "NUESTRA VISIÓN",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_4), // Imagen de la visión
            contentDescription = "Nuestra Visión",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "\"Imaginamos un mundo donde la menstruación no solo es sostenible para el planeta, sino también empoderadora para todas las mujeres.\"",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección Empoderamiento Económico en una sola columna
        Text(
            text = "EMPODERAMIENTO ECONÓMICO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Ahorro a Largo Plazo:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFDA1884),
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = "Al invertir en Zazil, estás invirtiendo en un producto que dura. Olvídate de compras mensuales.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Oportunidades de Emprendimiento:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF42B8D0),
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = "Zazil apoya programas que proporcionan oportunidades de emprendimiento.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_6), // Imagen superior
            contentDescription = "Empoderamiento Económico",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.img_7), // Imagen inferior
            contentDescription = "Empoderamiento Económico",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección Únete a Nosotros en una sola columna
        Text(
            text = "ÚNETE A NOSOTROS EN NUESTRA MISIÓN",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Cada apoyo, cada voz y cada esfuerzo cuentan. Únete a la Fundación \"Todas Brillamos AC\" en nuestra misión de crear un México más igualitario y libre de pobreza menstrual.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Box(
            modifier = Modifier
                .background(Color(0xFF7D4C88))
                .padding(16.dp)
        ) {
            Text(
                text = "Fundación Todas Brillamos A.C.\nBanorte\nCuenta: 1096319621\nClave: 072180010963196216\nContacto: +52 56 2808 3883",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Image(
            painter = painterResource(id = R.drawable.img_8), // Imagen final
            contentDescription = "Imagen Únete a la Misión",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
    }
}
