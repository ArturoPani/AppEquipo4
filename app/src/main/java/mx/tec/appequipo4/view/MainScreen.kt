package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.tec.appequipo4.R

@Composable
fun MainScreen(navController: NavController) {
    val backgroundColor = Color(0xFFFEE0D7)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTitle()
        Spacer(modifier = Modifier.height(16.dp))
        AppButton(text = "INICIAR SESIÓN", backgroundColor = Color(0xFFFF5722)) {
            // Acción de iniciar sesión
            navController.navigate("login")
        }
        AppButton(text = "REGISTRARSE", backgroundColor = Color(0xFFE91E63)) {
            // Navega a la pantalla de SignUp
            navController.navigate("signup") //Borrar cuando se implemente el model y view model
        }
        AppButton(text = "INVITADO", backgroundColor = Color(0xFF2196F3)) {
            // Acción para usuario invitado
            navController.navigate("catalogo")
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}