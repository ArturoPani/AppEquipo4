package mx.tec.appequipo4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
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
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    val textState = remember { mutableStateOf("") }

    Column(modifier = modifier
        .padding(vertical = 8.dp, horizontal = 32.dp)) {
        Text(text = label, color = Color.Black)
        BasicTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
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
                ){
                    innerTextField()
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}