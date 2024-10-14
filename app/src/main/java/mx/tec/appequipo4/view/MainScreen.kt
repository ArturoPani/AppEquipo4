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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

/**
 * Pantalla principal de la aplicación, donde se muestran los botones de inicio de sesión, registro y acceso como invitado.
 * @param navController El controlador de navegación de la aplicación.
 * @param usuarioViewModel El ViewModel que maneja la lógica de la autenticación de usuarios.
 */

@Composable
fun MainScreen(navController: NavController, usuarioViewModel: UsuarioViewModel) {
    val backgroundColor = Color(0xFFFEE0D7)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(vertical = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(300.dp)
            )
            AppTitle()
        //Botones de incio de sesión
        val color = Color(0xFFE91E63)
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(text = "INICIAR SESIÓN",
                backgroundColor = Color(0xFFE91E63),
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp))) {
                // Acción de iniciar sesión
                navController.navigate("login")
            }
            AppButton(text = "REGISTRARSE", backgroundColor = Color(0xFFE91E63),
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp))) {
                // Navega a la pantalla de SignUp
                println("apretamos Registrarse")
                navController.navigate("signup") //Borrar cuando se implemente el model y view model
            }
            AppButton(text = "INVITADO", backgroundColor = Color(0xFFE91E63),
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp))) {
                // Acción para usuario invitado
                navController.navigate("menu_principal")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}