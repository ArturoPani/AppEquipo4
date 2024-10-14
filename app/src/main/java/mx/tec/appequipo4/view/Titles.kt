package mx.tec.appequipo4.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.tec.appequipo4.R

/**
 * Composable que muestra el título de la aplicación.
 * @param modifier Modificadores para personalizar el diseño.
 */

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val naranja = Color(0xFFE8623D)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "ZAZIL",
            fontSize = 50.sp,
            fontFamily = customFont2,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
