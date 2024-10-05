package mx.tec.appequipo4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    label: String,
    value: String, // Pasamos el valor como parámetro
    onValueChange: (String) -> Unit, // Función para manejar los cambios en el texto
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 32.dp)
    ) {
        Text(text = label, color = Color.Black, fontSize = 18.sp)
        BasicTextField(
            value = value, // Usamos el valor proporcionado
            onValueChange = onValueChange, // Usamos la función proporcionada para manejar cambios
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
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
