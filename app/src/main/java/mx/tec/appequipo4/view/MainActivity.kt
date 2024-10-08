package mx.tec.appequipo4.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import mx.tec.appequipo4.viewModel.UsuarioViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val UsuarioViewModel = UsuarioViewModel()
            val navController = rememberNavController()
            NavGraph(navController = navController,UsuarioViewModel)
        }
    }
}
