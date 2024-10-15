package mx.tec.appequipo4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.tec.appequipo4.R

/**
 * Composable que representa un campo de entrada de texto personalizado.
 * @param label Etiqueta del campo de entrada.
 * @param value Valor actual del campo de entrada.
 * @param onValueChange Función que se llama cuando el valor del campo cambia.
 * @param modifier Modificadores para personalizar la apariencia y el comportamiento del campo.
 * @param isPassword Indica si el campo de entrada es de tipo contraseña.
 */

@Composable
fun InputField(
    label: String,
    value: String, // Pasamos el valor como parámetro
    onValueChange: (String) -> Unit, // Función para manejar los cambios en el texto
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions
) {
    val customFont = FontFamily(Font(R.font.bebasneue_regular))
    val customFont2 = FontFamily(Font(R.font.safira_march))
    val customFontPoppins = FontFamily(Font(R.font.poppins_regular))
    val customFontPoppinsextralight = FontFamily(Font(R.font.poppins_extralight))
    val customColor = Color(0xFFD22973)
    val azul = Color(0xFF5885C6)
    val amarillo = Color(0xFFFFD54F)
    val amarilloClaro = Color(0xFFFFFFFF)
    val naranja = Color(0xFFE8623D)
    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 32.dp)
    ) {
        Text(text = label, color = Color.Black, fontSize = 18.sp, fontFamily = customFontPoppins)
        BasicTextField(
            value = value, // Usamos el valor proporcionado
            onValueChange = onValueChange, // Usamos la función proporcionada para manejar cambios
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = customFontPoppins
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(amarilloClaro),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                ) {
                    innerTextField()
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}
