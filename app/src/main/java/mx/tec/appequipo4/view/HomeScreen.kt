@file:OptIn(ExperimentalMaterial3Api::class)

package mx.tec.appequipo4.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.tec.appequipo4.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    data class MenuItem(
        val icon: ImageVector,
        val text: String,
        val edgeColor:Color,
        val onClick: () -> Unit)

    //TODO: Agregar boton hacia la pantalla del inventario para el administrador
    //TODO: Reemplazar iconos con otros mas adecuados

    val menuItems = listOf(
        MenuItem(Icons.Filled.Person, "Mis Datos", Color(0xFFF44336), {/* navController.navigate("profile")*/}),
        MenuItem(Icons.Filled.Menu, "Catálogo", Color(0xFFF44336), { /* navController.navigate("catalogo")*/}),
        MenuItem(Icons.Filled.Favorite, "Mis Pedidos", Color(0xFFF44336), {/* navController.navigate("historial") */}),
        MenuItem(Icons.Filled.ShoppingCart, "Carrito", Color(0xFFF44336), {/* navController.navigate("carrito") */}),
        MenuItem(Icons.Filled.Home, "Conoce ZAZIl", Color(0xFFF44336), {/* navController.navigate("conocenos") */}),
        MenuItem(Icons.Filled.Info, "Preguntas Frecuentes, Contacto", Color(0xFFF44336), { /* navController.navigate("FAQ") */})
    )


    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { /*TODO*/ },
                containerColor = Color(0xFFE76141),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Ayuda",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { contentPadding ->
        // Fondo de pantalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(0xFFFEE0D7)
//                brush = Brush.linearGradient(
//                    colors = listOf(Color.White, Color(0xFFFEE0D7))
//                )
                )
                .padding(contentPadding)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Zazil con nombre",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .alpha(0.4f)
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(48.dp))

//           //Logo ZAZIL, TODO:cambiar texto por imagen del nombre con la tipografia adecuada
                Text(
                    text = "ZAZIL",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 60.sp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 40.dp)
                        .fillMaxWidth(0.8f)
                )


                // Botones del menu principal
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(16.dp),
                ) {
                    items(menuItems.size) { index ->
                        val item = menuItems[index]
                        TiledButton(
                            icon = item.icon,
                            text = item.text,
                            edgeColor = item.edgeColor,
                            onClick = item.onClick
                        )
                    }
                }


// Leyenda Copyright
                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.logo_signup1),
                    contentDescription = "Logo Zazil con nombre",
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 1.dp)
                )
// TODO: Quitar el fondo de la imagen
                Text(
                    text = "© 2023 Fundacion Todas Brillamos, A. C.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}
