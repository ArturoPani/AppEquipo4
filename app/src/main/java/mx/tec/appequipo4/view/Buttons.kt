package mx.tec.appequipo4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.tec.appequipo4.R

/**
 * Botón personalizado para la aplicación.
 * @param text Texto del botón.
 * @param backgroundColor Color de fondo del botón.
 * @param modifier Modificadores para personalizar el botón.
 * @param onClick Acción a realizar cuando se hace clic en el botón.
 */

@Composable
fun AppButton(
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
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
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .height(50.dp)
    ) {
        Text(text = text, fontSize = 16.sp, color = Color.White, fontFamily = customFont)
    }
}