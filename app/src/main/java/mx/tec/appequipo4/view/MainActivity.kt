package mx.tec.appequipo4.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.tec.appequipo4.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val backgroundColor = Color(0xFFFEE0D7)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(0.1.dp))
        AppTitle()
        Spacer(modifier = Modifier.height(16.dp))
        AppButton(text = "INICIAR SESIÓN", backgroundColor = Color(0xFFFF5722)) {
            // TODO: Implement onClick
        }
        AppButton(text = "REGISTRARSE", backgroundColor = Color(0xFFE91E63)) {
            // TODO: Implement onClick
        }
        AppButton(text = "INVITADO", backgroundColor = Color(0xFF2196F3)) {
            // TODO: Implement onClick
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
