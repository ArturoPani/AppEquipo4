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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
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
import mx.tec.appequipo4.R
import mx.tec.appequipo4.viewModel.UsuarioViewModel

@Composable
fun SignUpScreen(navController: NavController, viewModel: UsuarioViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFFEE0D7), Color(0xFFE8A6C9), Color(0xFFD22973))
    )
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))

    // Variables para manejar los errores
    var emailError by remember { mutableStateOf("") }
    var edadError by remember { mutableStateOf("") }
    var curpError by remember { mutableStateOf("") }
    var telefonoError by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
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
                        .clickable { navController.popBackStack() }
                        .padding(8.dp),
                    tint = Color.White
                )
            }

            Image(
                painter = painterResource(id = R.drawable.zazil_logo),
                contentDescription = "Logo Zazil",
                modifier = Modifier
                    .size(180.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Crear Cuenta",
                fontFamily = customFont,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campos de texto conectados al ViewModel con separación adicional
            InputField(
                label = "Nombre",
                value = viewModel.nombre.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = { viewModel.onNombreChange(it) },
                keyboardOptions = KeyboardOptions.Default
            )
            InputField(
                label = "Apellidos",
                value = viewModel.apellido.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = { viewModel.onApellidoChange(it) },
                keyboardOptions = KeyboardOptions.Default
            )
            InputField(
                label = "Correo electrónico",
                value = viewModel.email.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = {
                    viewModel.onEmailChange(it)
                    emailError = if (!it.contains("@")) "Correo inválido" else ""
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            if (emailError.isNotEmpty()) {
                Text(text = emailError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
            }

            InputField(
                label = "Contraseña",
                value = viewModel.contraseña.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = { viewModel.onContraseñaChange(it) },
                isPassword = true,
                keyboardOptions = KeyboardOptions.Default
            )
            InputField(
                label = "Confirmar Contraseña",
                value = viewModel.confirmarContraseña.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = { viewModel.onConfirmarContraseñaChange(it) },
                isPassword = true,
                keyboardOptions = KeyboardOptions.Default
            )
            InputField(
                label = "Teléfono",
                value = viewModel.telefono.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = {
                    viewModel.onTelefonoChange(it)
                    telefonoError = if (it.length != 10) "El teléfono debe tener 10 dígitos" else ""
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            if (telefonoError.isNotEmpty()) {
                Text(text = telefonoError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
            }

            InputField(
                label = "Edad",
                value = viewModel.edad.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = {
                    viewModel.onEdadChange(it)
                    edadError = if (it.toIntOrNull() == null) "Ingresa una edad válida" else ""
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (edadError.isNotEmpty()) {
                Text(text = edadError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
            }

            InputField(
                label = "CURP",
                value = viewModel.curp.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, SolidColor(Color(0xFFEBEBEB)), RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                onValueChange = {
                    viewModel.onCurpChange(it)
                    curpError = if (it.length != 18) "El CURP debe tener 18 caracteres" else ""
                },
                keyboardOptions = KeyboardOptions.Default
            )
            if (curpError.isNotEmpty()) {
                Text(text = curpError, color = Color.Red, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            Button(
                onClick = {
                    if (emailError.isEmpty() && telefonoError.isEmpty() && edadError.isEmpty() && curpError.isEmpty()) {
                        viewModel.crearUsuario()
                        navController.navigate("login")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Text("REGISTRARSE", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AVISO DE PRIVACIDAD",
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = customFontPoppinsextralight,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navController.navigate("aviso_privacidad")
                    },
                textAlign = TextAlign.Center
            )
        }
    }
}
