package mx.tec.appequipo4.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mx.tec.appequipo4.viewModel.UsuarioViewModel  // Asegúrate de importar tu ViewModel

@Composable
fun CatalogoScreen(navController: NavController, viewModel: UsuarioViewModel = viewModel()) {
    // Observamos los productos del ViewModel
    val productos = viewModel.productos.observeAsState(initial = emptyList())

    // Mostramos los productos en una LazyColumn
    LazyColumn {
        items(productos.value) { producto ->
            ProductoComposable(product = producto) {
                // Aquí puedes manejar lo que ocurre al hacer clic en un producto
            }
        }
    }

    // Llamamos a la función para cargar los productos desde el backend
    viewModel.obtenerProductosVM()
}
