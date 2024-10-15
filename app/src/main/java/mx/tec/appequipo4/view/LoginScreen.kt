package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: UsuarioViewModel) {
    val scrollState = rememberScrollState()
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFFEE0D7), Color(0xFFE8A6C9), Color(0xFFD22973))
    )
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Estado del Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val isAuthenticated by viewModel.isAuthenticated.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp)
                    .clickable { navController.popBackStack() },
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Logo Zazil
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Zazil",
                modifier = Modifier
                    .size(200.dp)
                    .padding(vertical = 16.dp),
                alignment = Alignment.Center
            )

            Text(
                text = "Iniciar Sesión",
                fontFamily = customFont,
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo de texto para el usuario
            InputField(
                label = "E-mail",
                value = viewModel.email.value,
                onValueChange = {
                    viewModel.onEmailChange(it)
                    emailError = if (!it.contains("@")) "Correo inválido" else ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, Color.White, RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            if (emailError.isNotEmpty()) {
                Text(text = emailError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
            }

            // Campo de texto para la contraseña
            InputField(
                label = "Contraseña",
                value = viewModel.contraseña.value,
                onValueChange = {
                    viewModel.onContraseñaChange(it)
                    passwordError = if (it.length < 6) "La contraseña es muy corta" else ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, Color.White, RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                isPassword = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            if (passwordError.isNotEmpty()) {
                Text(text = passwordError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Login
            Button(
                onClick = {

                        viewModel.iniciarSesionVM()

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Text("INICIAR SESION", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AVISO DE PRIVACIDAD",
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = customFontPoppinsextralight,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navController.navigate("aviso_privacidad") },
                textAlign = TextAlign.Center
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
            navController.navigate("menu_principal")
        } else {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "Error al iniciar sesión, verifica tus datos",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}
