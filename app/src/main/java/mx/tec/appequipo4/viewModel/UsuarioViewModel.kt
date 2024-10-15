package mx.tec.appequipo4.viewModel

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.tec.appequipo4.model.Compra
import mx.tec.appequipo4.model.LoginUsuario
import mx.tec.appequipo4.model.Product
import mx.tec.appequipo4.model.ProductHistorial
import mx.tec.appequipo4.model.Usuario
import mx.tec.appequipo4.model.iniciarSesion
import mx.tec.appequipo4.model.obtenerProductos
import mx.tec.appequipo4.model.obtenerProductosHistorial
import mx.tec.appequipo4.model.registrarCompra
import mx.tec.appequipo4.model.registrarUsuario
import java.util.Date
import java.util.Locale

class UsuarioViewModel : ViewModel() {

    var emailCompra:String = ""

    private val _usuario = mutableStateOf<Usuario?>(null)
    val usuario: MutableState<Usuario?> = _usuario

    private val _compra = mutableStateOf<(Compra?)>(null)
    val compra: MutableState<Compra?> = _compra
    private val _loginUsuario = mutableStateOf<LoginUsuario?>(null)
    val loginUsuario: MutableState<LoginUsuario?> = _loginUsuario

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> get() = _isAuthenticated

    private val _emailCompras = MutableStateFlow("")
    val emailCompras: StateFlow<String> get() = _emailCompras

    private val _productos = MutableLiveData<List<Product>>()
    val productos: LiveData<List<Product>> get() = _productos

    private val _productosHistorial = MutableLiveData<List<ProductHistorial>>()
    val productosHistorial: LiveData<List<ProductHistorial>> get() = _productosHistorial

    // Estado del carrito
    private val _carrito = MutableLiveData<List<Product>?>(emptyList())
    val carrito: MutableLiveData<List<Product>?> get() = _carrito

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

    /**
     * Actualiza el nombre del usuario.
     * @param newNombre Nuevo nombre del usuario.
     */

    fun onNombreChange(newNombre: String) {
        nombre.value = newNombre
    }

    /**
     * Actualiza el apellido del usuario.
     * @param newApellido Nuevo apellido del usuario.
     */

    fun onApellidoChange(newApellido: String) {
        apellido.value = newApellido
    }

    /**
     * Actualiza el email del usuario.
     * @param newEmail Nuevo email del usuario.
     */

    fun onEmailChange(newEmail: String) {
        email.value = newEmail
        emailCompra = newEmail
        println("on email change called with newEmail: $emailCompra")
    }

    /**
     * Actualiza la contraseña del usuario.
     * @param newContraseña Nueva contraseña del usuario.
     */

    fun onContraseñaChange(newContraseña: String) {
        contraseña.value = newContraseña
    }

    /**
     * Actualiza la confirmación de la contraseña del usuario.
     * @param newConfirmarContraseña Nueva confirmación de contraseña del usuario.
     */

    fun onConfirmarContraseñaChange(newConfirmarContraseña: String) {
        confirmarContraseña.value = newConfirmarContraseña
    }

    /**
     * Actualiza el teléfono del usuario.
     * @param newTelefono Nuevo teléfono del usuario.
     */

    fun onTelefonoChange(newTelefono: String) {
        telefono.value = newTelefono
    }

    /**
     * Actualiza la edad del usuario.
     * @param newEdad Nueva edad del usuario.
     */

    fun onEdadChange(newEdad: String) {
        edad.value = newEdad
    }

    /**
     * Actualiza la CURP del usuario.
     * @param newCurp Nueva CURP del usuario.
     */

    fun onCurpChange(newCurp: String) {
        curp.value = newCurp
    }

    var emailSuperBueno = ""
    // Función para crear un Usuario
    /**
     * Crea un nuevo usuario con la información proporcionada.
     */

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


    /**
     * Inicia sesión con las credenciales proporcionadas.
     */

    fun iniciarSesionVM() {
        val nuevoUsuario = LoginUsuario(
            email = email.value,
            contraseña = contraseña.value,
        )
        val emailBueno = email.value
        emailSuperBueno = emailBueno
        _loginUsuario.value = nuevoUsuario
        iniciarSesion(_loginUsuario) { success ->
            if (success) {

                println("Inicio de sesión exitoso")
                emailCompra = email.value
                emailSuperBueno
                _isAuthenticated.value = true

                println("Estado de autenticación en vm: $_isAuthenticated")
                // Aquí puedes actualizar el estado de la ViewModel o navegar a otra pantalla
            } else {
                println("Error de autenticación")
                _isAuthenticated.value = false

            }
        }
        _emailCompras.value = emailSuperBueno
        println("Estado de email al final de IniciarSesion: ${emailSuperBueno}")
    }

    /**
     * Obtiene la lista de productos.
     */

    fun obtenerProductosVM() {
        try {
            obtenerProductos { productoList ->
                if (productoList.isNotEmpty()) {
                    _productos.value = productoList
                    println("Lista obtenida: $productoList")  // Imprime la lista obtenida
                } else {
                    println("No se encontraron productos")
                    _productos.value = emptyList()  // En caso de que la lista esté vacía
                }
            }
        } catch (e: Exception) {
            println("Error al obtener productos: ${e.message}")
            _productos.value = emptyList()  // En caso de error, asigna una lista vacía
        }
    }

    /**
     * Obtiene un producto por su ID.
     * @param productId ID del producto a buscar.
     * @return El producto encontrado o null si no se encuentra.
     */

    fun getProductById(productId: String?): Product? {
        val productos = _productos.value

        // Si la lista de productos es nula o está vacía, intenta obtener los productos
        if (productos == null || productos.isEmpty()) {
            println("Lista de productos vacía o no cargada. Intentando cargar productos...")
            obtenerProductosVM()  // Llama a la función para obtener los productos

            // Después de intentar cargar, revisamos de nuevo si los productos están disponibles
            val productosActualizados = _productos.value
            if (productosActualizados == null || productosActualizados.isEmpty()) {
                println("Productos aún no cargados después de intentar obtenerlos.")
                return null  // Si los productos aún no se cargan, regresamos null
            }
        }

        // Si los productos están disponibles, realiza la búsqueda por ID
        println("Buscando producto con ID: $productId")
        return productos?.find { it.product_id.toString() == productId }
    }

    // Funcionalidad del carrito

    // Agregar un producto al carrito

    /**
     * Agrega un producto al carrito.
     * @param product Producto a agregar al carrito.
     */

    fun agregarProductoAlCarrito(product: Product) {
        println("si se llama agregar carrito")
        val carritoActualizado = _carrito.value?.toMutableList()?.apply {
            add(product)
        }
        _carrito.value = carritoActualizado
        println("carrito actualizado: ${_carrito.value}")
    }

    /**
     * Elimina un producto del carrito.
     * @param product Producto a eliminar del carrito.
     */
    // Eliminar un producto del carrito
    fun eliminarProductoDelCarrito(product: Product) {
        val carritoActualizado = _carrito.value?.toMutableList()?.apply {
            remove(product)
        }
        _carrito.value = carritoActualizado
    }

    /**
     * Vaciar el carrito.
     */
    // Vaciar el carrito
    fun vaciarCarrito() {
        _carrito.value = emptyList()
    }

    fun cerrarSesion(){
        _isAuthenticated.value = false
    }


    /**
     * Mostrar el estado del carrito.
     * @return Lista de productos en el carrito.
     */
    // Mostrar el estado del carrito
    fun mostrarCarrito(): List<Product>? {

        return _carrito.value
    }


    /**
     * Realiza el checkout.
     * @param email Email del usuario.
     */
    fun hacerCheckoutVM(email: String) {
        println("Compra registrada con éxito1")
        println("EmailCompra: $email")
        // Verifica que haya un usuario y que el carrito no esté vacío
        val nuevaCompra = Compra(
            curp = email,
            products = _carrito.value ?: emptyList()  // Pasa la lista de productos desde el carrito
        )


        // Asigna el valor a la variable `_compra`
        _compra.value = nuevaCompra
        registrarCompra(_compra)
    }



    fun obtenerProductosHistorialVM(email: String) {
        try {
            obtenerProductosHistorial({ productoList ->
                if (productoList.isNotEmpty()) {
                    _productosHistorial.value = productoList
                    println("Lista obtenida: $productoList")  // Imprime la lista obtenida
                } else {
                    println("No se encontraron productos")
                    _productosHistorial.value = emptyList()  // En caso de que la lista esté vacía
                }
            }, email) // Asegúrate de pasar el 'email' aquí
        } catch (e: Exception) {
            println("Error al obtener productos: ${e.message}")
            _productosHistorial.value = emptyList()  // En caso de error, asigna una lista vacía
        }
    }

}
