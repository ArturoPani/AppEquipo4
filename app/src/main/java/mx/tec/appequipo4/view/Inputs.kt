package mx.tec.appequipo4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    val textState = remember { mutableStateOf("") }

    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(text = label, color = Color.Black)
        BasicTextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(50.dp)
                .background(Color.LightGray)
        )
    }
}
