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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

/**
 * Pantalla que muestra el login de la aplicación
 * @param navController Controlador de navegación de la aplicación
 * @param viewModel Modelo de vista del usuario
 */

@Composable
fun LoginScreen(navController: NavController, viewModel: UsuarioViewModel) {
    val scrollState = rememberScrollState()
    val backgroundColor = Color(0xFFFEE0D7)
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val amarilloClaro = Color(0xFFFFFFFF)
    val naranja = Color(0xFFE8623D)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // Observa el estado de autenticación
    val isAuthenticated by viewModel.isAuthenticated.collectAsState()

    var isLogin = false

    // Estado del Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
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
                value = viewModel.email.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onEmailChange(it) }
            )

            // Campo de texto para la contraseña
            InputField(
                label = "Contraseña",
                value = viewModel.contraseña.value,
                modifier = Modifier.background(amarilloClaro),
                onValueChange = { viewModel.onContraseñaChange(it) },
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
                //isLogin = true
                viewModel.iniciarSesionVM() //mandamos a llamar a iniciar sesion
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
        // Mostrar el Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

    // Efecto lanzado que observa el estado de autenticación
    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            println("Estado de autenticación: $isAuthenticated")
            navController.navigate("menu_principal") {
                //popUpTo("login") { inclusive = true }  // Opcional: Remover la pantalla de login del backstack
            }
        }
        //else {
            // Mostrar el Snackbar cuando falle la autenticación
            //coroutineScope.launch {
            //    snackbarHostState.showSnackbar(
            //        message = "Error al iniciar sesión, no se pudo verificar la cuenta",
            //        duration = SnackbarDuration.Short
            //    )

        //}

    }
}