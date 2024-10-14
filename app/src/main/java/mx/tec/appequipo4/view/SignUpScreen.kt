package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

/**
 * Pantalla de registro de usuario.
 * @param navController Controlador de navegación.
 * @param viewModel Modelo de vista del usuario.
 */

@Composable
fun SignUpScreen(navController: NavController, viewModel: UsuarioViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val backgroundColor = Color(0xFFFEE0D7)
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val amarilloClaro = Color(0xFFFFECB3)
    val naranja = Color(0xFFE8623D)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
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

            // Campos de texto conectados al ViewModel
            InputField(
                label = "Nombre",
                value = viewModel.nombre.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onNombreChange(it) }
            )
            InputField(
                label = "Apellidos",
                value = viewModel.apellido.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onApellidoChange(it) }
            )
            InputField(
                label = "Correo electrónico",
                value = viewModel.email.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onEmailChange(it) }
            )
            InputField(
                label = "Contraseña",
                value = viewModel.contraseña.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onContraseñaChange(it) },
                isPassword = true
            )
            InputField(
                label = "Confirmar Contraseña",
                value = viewModel.confirmarContraseña.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onConfirmarContraseñaChange(it) },
                isPassword = true
            )
            InputField(
                label = "Teléfono",
                value = viewModel.telefono.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onTelefonoChange(it) }
            )
            InputField(
                label = "Edad",
                value = viewModel.edad.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onEdadChange(it) }
            )
            InputField(
                label = "CURP",
                value = viewModel.curp.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onCurpChange(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            AppButton(
                text = "REGISTRARSE",
                backgroundColor = Color(0xFFE91E63),
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp)
                    .background(color = Color(0xFFE91E63), shape = RoundedCornerShape(16.dp))
            ) {
                println("Email: ${viewModel.email.value}")
                viewModel.crearUsuario()

                // Aquí podrías enviar los datos del usuario a la base de datos
                // y navegar a la pantalla de login.
                navController.navigate("login")

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AVISO DE PRIVACIDAD",
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = customFontPoppinsextralight,
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