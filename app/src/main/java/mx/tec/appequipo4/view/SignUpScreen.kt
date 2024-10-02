package mx.tec.appequipo4.view


import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SignUpScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val backgroundColor = Color(0xFFFEE0D7)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
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
        }
        Image(
            painter = painterResource(id = R.drawable.zazil_logo),
            contentDescription = "Logo Zazil",
            modifier = Modifier
                .size(200.dp),
            contentScale = ContentScale.Crop
        )

        // Campos de texto
        InputField(label = "Nombre")
        InputField(label = "Usuario")
        InputField(label = "Correo electrónico")
        InputField(label = "Contraseña", isPassword = true)
        InputField(label = "Confirmar Contraseña", isPassword = true)
        InputField(label = "Teléfono")
        InputField(label = "Edad")
        InputField(label = "CURP")

        Spacer(modifier = Modifier.height(16.dp))
        val color = Color(0xFFE91E63)
        // Botón de registro
        AppButton(text = "REGISTRARSE",
            backgroundColor = Color(0xFFE91E63),
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp))){

            navController.navigate("login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Aviso de privacidad",
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
                .clickable {
                    // Navegar a la página de aviso de privacidad
                    navController.navigate("aviso_privacidad")
                },
            textAlign = TextAlign.Right
        )
    }
}
