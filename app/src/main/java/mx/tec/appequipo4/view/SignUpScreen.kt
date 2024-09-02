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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import mx.tec.appequipo4.R

@Composable
fun SignUpScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Fila para los logos y el título en la parte superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_signup1),
                contentDescription = "Logo Izquierdo",
                modifier = Modifier.size(80.dp)
            )

            AppTitle(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Logo Derecho",
                modifier = Modifier.size(80.dp)
            )
        }

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

        // Botón de registro
        AppButton(text = "REGISTRARSE", backgroundColor = Color(0xFFE91E63)) {
            // Acciones al hacer clic en Registrarse
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Aviso de privacidad", color = Color.Gray)
    }
}
