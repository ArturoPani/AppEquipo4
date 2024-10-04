package mx.tec.appequipo4.viewModel

import android.content.Context
import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.tec.appequipo4.model.LoginUsuario
import mx.tec.appequipo4.model.Product
import mx.tec.appequipo4.model.RetrofitClient
import mx.tec.appequipo4.model.Usuario
import mx.tec.appequipo4.model.getProductos
import mx.tec.appequipo4.model.iniciarSesion
import mx.tec.appequipo4.model.obtenerProductos
import mx.tec.appequipo4.model.registrarUsuario
import retrofit2.Call
import retrofit2.Response
import java.util.Date
import java.util.Locale

class UsuarioViewModel : ViewModel() {
    private val _usuario = mutableStateOf<Usuario?>(null)
    val usuario: MutableState<Usuario?> = _usuario


    private val _loginUsuario = mutableStateOf<LoginUsuario?>(null)
    val loginUsuario: MutableState<LoginUsuario?> = _loginUsuario

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> get() = _isAuthenticated


    private val _productos = MutableLiveData<List<Product>>()
    val productos: LiveData<List<Product>> get() = _productos

    var nombre = mutableStateOf("")
        private set
    var apellido = mutableStateOf("")
        private set
    var email = mutableStateOf("")
        private set
    var contraseña = mutableStateOf("")
        private set
    var confirmarContraseña = mutableStateOf("")
        private set
    var telefono = mutableStateOf("")
        private set
    var edad = mutableStateOf("")
        private set
    var curp = mutableStateOf("")
        private set

    fun onNombreChange(newNombre: String) {
        nombre.value = newNombre
    }

    fun onApellidoChange(newApellido: String) {
        apellido.value = newApellido
    }

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
    }

    fun onContraseñaChange(newContraseña: String) {
        contraseña.value = newContraseña
    }

    fun onConfirmarContraseñaChange(newConfirmarContraseña: String) {
        confirmarContraseña.value = newConfirmarContraseña
    }

    fun onTelefonoChange(newTelefono: String) {
        telefono.value = newTelefono
    }

    fun onEdadChange(newEdad: String) {
        edad.value = newEdad
    }

    fun onCurpChange(newCurp: String) {
        curp.value = newCurp
    }

    // Función para crear un Usuario
    fun crearUsuario() {
        val createdAt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        println(email.value)
        val nuevoUsuario = Usuario(
            curp = curp.value,
            edad = edad.value,
            nombre = nombre.value,
            apellido = apellido.value,
            email = email.value,
            contraseña = contraseña.value,
            telefono = telefono.value,
            creacion = createdAt
        )
        _usuario.value = nuevoUsuario
        registrarUsuario(_usuario)
    }


    fun iniciarSesionVM() {
        val nuevoUsuario = LoginUsuario(
            email = email.value,
            contraseña = contraseña.value,
        )
        _loginUsuario.value = nuevoUsuario
        iniciarSesion(_loginUsuario) { success ->
            if (success) {

                println("Inicio de sesión exitoso")
                _isAuthenticated.value = true

                println("Estado de autenticación en vm: $_isAuthenticated")
                // Aquí puedes actualizar el estado de la ViewModel o navegar a otra pantalla
            } else {
                println("Error de autenticación")
                _isAuthenticated.value = false

            }
        }
    }





    fun obtenerProductosVM() {
        obtenerProductos { productoList ->
            _productos.value = productoList
            println("lista: $productoList") // Imprime la lista obtenida
        }
    }
}
