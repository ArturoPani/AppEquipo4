package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

/**
 * Pantalla de conocenos donde aparece la información de la fundación (misión, visión, etc.)
 *
 */
val customFont = FontFamily(Font(R.font.bebasneue_regular))
val customFont2 = FontFamily(Font(R.font.safira_march))
val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
val customColor = Color(0xFFD22973)
val azul = Color(0xFF5885C6)
val amarillo = Color(0xFFFFD54F)
val naranja = Color(0xFFE8623D)

@Composable
fun ConocenosScreen(navController: NavController, viewModel: UsuarioViewModel) {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() }
            )
        }
        Image(
            painter = painterResource(id = R.drawable.zazil_logo),
            contentDescription = "Logo Zazil",
            modifier = Modifier
                .size(200.dp),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "Virydiana Fernández",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(50))
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "\"Juntos, iluminaremos el camino hacia una generación libre, informada y empoderada.\" \n ¡Libertad para Todas! \n\n Virydiana Fernández \n Presidenta Fundación Todas Brillamos AC",
            fontSize = 25.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = customFont,
            color = customColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "¿QUIÉNES SOMOS?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = customFont2,
            color = naranja,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_2),
            contentDescription = "Producto Zazil",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(50))
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Zazil es una marca comprometida con el bienestar de las mujeres y el cuidado del medio ambiente. Su misión es proporcionar soluciones innovadoras y sostenibles para el período menstrual. ¿Cómo lo hacen? A través de la creación de toallas femeninas reutilizables.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = customFontPoppins,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )


        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "\"Cambiando el Mundo, un Ciclo a la Vez.\"",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = customFont,
            color = customColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "NUESTRA MISIÓN",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = naranja,
            fontFamily = customFont2,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_3),
            contentDescription = "Nuestra Misión",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(50))
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "En Zazil, no solo estamos redefiniendo la menstruación, sino también el impacto que tiene en la economía y el medio ambiente. Nuestra misión es empoderar a las mujeres a tomar decisiones informadas sobre su salud menstrual mientras generan un impacto positivo en su bienestar financiero y en el planeta.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontFamily = customFontPoppins,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "NUESTRA VISIÓN",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = naranja,
            fontFamily = customFont2,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_4),
            contentDescription = "Nuestra Visión",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(50))
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "\"Imaginamos un mundo donde la menstruación no solo es sostenible para el planeta, sino también empoderadora para todas las mujeres.\"",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = customFontPoppinsextralight,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "EMPODERAMIENTO ECONÓMICO",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = naranja,
            fontFamily = customFont2,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Ahorro a Largo Plazo:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = customFontPoppinsextralight,
            color = azul,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = "Al invertir en Zazil, estás invirtiendo en un producto que dura. Olvídate de compras mensuales.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = customFontPoppins,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Oportunidades de Emprendimiento:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = customFontPoppinsextralight,
            color = azul,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Text(
            text = "Zazil apoya programas que proporcionan oportunidades de emprendimiento.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = customFontPoppins,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_6),
            contentDescription = "Empoderamiento Económico",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(50))
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.img_7),
            contentDescription = "Empoderamiento Económico",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(50))
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "ÚNETE A NOSOTROS EN NUESTRA MISIÓN",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = naranja,
            fontFamily = customFont2,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Cada apoyo, cada voz y cada esfuerzo cuentan. Únete a la Fundación \"Todas Brillamos AC\" en nuestra misión de crear un México más igualitario y libre de pobreza menstrual.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = customFontPoppins,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Box(
            modifier = Modifier
                .background(azul)
                .padding(16.dp)
        ) {
            Text(
                text = "Fundación Todas Brillamos A.C.\nBanorte\nCuenta: 1096319621\nClave: 072180010963196216\nContacto: +52 56 2808 3883",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = customFont,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.img_8),
            contentDescription = "Imagen Únete a la Misión",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(50))
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
    }
}
