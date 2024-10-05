package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
 * Pantalla que muestra el menú principal de la aplicación, incluye los botones para navegar al catalogo, historial de compras, conócenos y salir.
 */


@Composable
fun MenuPrincipal(navController: NavController) {
    val backgroundColor = Color(0xFFFEE0D7)
    val customFont2 = FontFamily(Font(R.font.poppins_extralightitalic))
fun MenuPrincipal(navController: NavController,viewModel: UsuarioViewModel = viewModel()) {
    // Fondo degradado
    val backgroundColor = Color(0xFFFEE0D7)
    val productos by viewModel.productos.observeAsState(emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp), // Ajustar espacio en la parte inferior
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen grande en la parte superior
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Imagen logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )
            AppTitle(modifier = Modifier.align(Alignment.CenterHorizontally))

            // Botón para navegar al Catálogo
            Column {
                val color = Color(0xFFE91E63)
                AppButton(
                    text = "CATÁLOGO",
                    backgroundColor = Color(0xFFE91E63),
                    text = "Catálogo",
                    backgroundColor = color,
                    modifier = Modifier.padding(bottom = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp)),
                    onClick = {
                        // Ejecuta ambas acciones



                        viewModel.obtenerProductosVM()
                        println("Lista de productos: $productos")
                        navController.navigate("catalogo") // Navega a la pantalla del catálogo
                    }
                )

                // Botón para navegar al Historial de Compras
                AppButton(
                    text = "HISTORIAL DE COMPRAS",
                    backgroundColor = Color(0xFFE91E63),
                    modifier = Modifier.padding(bottom = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp)),
                    onClick = { navController.navigate("historial") }
                )

                // Botón para navegar a Conócenos
                AppButton(
                    text = "CONÓCENOS",
                    backgroundColor = Color(0xFFE91E63),
                    modifier = Modifier.padding(bottom = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp)),
                    onClick = { navController.navigate("conocenos") }
                )

                // Botón "Regresar" para volver a la página principal
                AppButton(
                    text = "REGRESAR",
                    backgroundColor = Color(0xFFE91E63), // Color púrpura, por ejemplo
                    modifier = Modifier.padding(bottom = 16.dp).background(color = color, shape = RoundedCornerShape(16.dp)),
                    onClick = { navController.navigate("main") }
                )
            }

            Spacer(modifier = Modifier.height(32.dp)) // Agregar más espacio entre botones y el aviso
        }

        // Texto de "Aviso de privacidad" en la parte inferior derecha
        Text(
            text = "Aviso de privacidad",
            fontSize = 12.sp,
            fontFamily = customFont2,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    // Navegar a la página de aviso de privacidad
                    navController.navigate("aviso_privacidad")
                },
            textAlign = TextAlign.Right
        )
    }
}
