package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R

/**
 * Pantalla que muestra el login de la aplicación
 */

@Composable
fun LoginScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val backgroundColor = Color(0xFFFEE0D7)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() } // Volver a la página anterior
            )

            Spacer(modifier = Modifier.height(48.dp))

            //Logo ZAZIL
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Zazil",
                modifier = Modifier
                    .size(270.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp)
            )
            AppTitle(modifier = Modifier.align(Alignment.CenterHorizontally))

            // Campo de texto para el usuario
            InputField(
                label = "E-mail",
                value = email,
                onValueChange = { email = it }
            )

            // Campo de texto para la contraseña
            InputField(
                label = "Contraseña",
                value = password,
                onValueChange = { password = it },
                isPassword = true
            )

            val color = Color(0xFFE91E63)
            // Botón de Login
            AppButton(
                text = "INICIAR SESION",
                backgroundColor = Color(0xFFE91E63),
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp)
                    .background(color = color, shape = RoundedCornerShape(16.dp)),
            ) {
                // TODO: Acciones al hacer clic en Iniciar Sesion
                navController.navigate("menu_principal")
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
}
