package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mx.tec.appequipo4.R
import mx.tec.appequipo4.model.Product

/**
 * Composable que representa un producto en la lista de productos en el catálogo.
 * @param product El producto a mostrar.
 * @param onClick La acción a realizar cuando se hace clic en el producto.
 */

@Composable
fun ProductoComposable(product: Product, onClick: () -> Unit) {
    val context = LocalContext.current
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val naranja = Color(0xFFE8623D)

    val imageResourceId = remember(product.image_route) {
        context.resources.getIdentifier(product.image_route, "drawable", context.packageName)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Imagen del producto
            println("product.imageUrl: ${product.image_route}")

            Image(
                //"D:/5_Semestre_ITESM/appequipo4/app/src/main/res/drawable/image_kit3.png"
                //product.image_route

                //painter = rememberAsyncImagePainter(model = "D:/5_Semestre_ITESM/appequipo4/app/src/main/res/drawable/image_kit3.png"),

                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            // Detalles del producto
            Column(modifier = Modifier.weight(2f)) {
                Text(text = product.name, color = Color.Black, fontFamily = customFontPoppins)
                Text(text = "\$${product.price}", color = Color.Gray, fontFamily = customFontPoppinsextralight)
            }
        }
    }
}
