package mx.tec.appequipo4.viewModel

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import mx.tec.appequipo4.model.Usuario
import mx.tec.appequipo4.model.registrarUsuario
import java.util.Date
import java.util.Locale


class UsuarioViewModel : ViewModel() {
    private val _usuario = mutableStateOf<Usuario?>(null)
    val usuario: MutableState<Usuario?> = _usuario

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

        // Crear la instancia de Usuario
        val nuevoUsuario = Usuario(
            curp = curp.value,
            edad = edad.value,
            nombre = nombre.value,
            apellido = apellido.value,
            email = email.value,
            contraseña = contraseña.value,
            telefono = telefono.value,
            createdAt = createdAt
        )

        // Asignar el nuevo Usuario a _usuario
        _usuario.value = nuevoUsuario

        // Llamar a la función registrarUsuario
        registrarUsuario(nuevoUsuario)
    }
}

