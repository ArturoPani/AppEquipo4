package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R

@Composable
fun LoginScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFFFEE0D7))
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(48.dp))

            //Logo ZAZIL
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Logo Zazil con nombre",
                modifier = Modifier
                    .size(270.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp)
            )

            Text(
                text = "Bienvenida",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Campo de texto para el usuario
            InputField(label = "E-mail")
            // Campo de texto para la contraseña
            InputField(
                label = "Contraseña",
                isPassword = true
            )

            // Botón de Login
            AppButton(
                text = "INICIAR SESION",
                backgroundColor = Color(0xFFE76141)
            ) {
                //TODO: Acciones al hacer clic en Iniciar Sesion
            }

            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.logo_signup1),
                contentDescription = "Logo Zazil con nombre",
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Aviso de privacidad",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )
        }
    }
}