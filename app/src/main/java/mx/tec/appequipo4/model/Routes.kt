package mx.tec.appequipo4.model
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun obtenerUsuarios() {
    RetrofitClient.instance.obtenerUsuarios().enqueue(object : Callback<List<Usuario>> {
        override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
            if (response.isSuccessful) {
                val usuarios = response.body()
                // Maneja la lista de usuarios aquí
            } else {
                // Maneja el error aquí
            }
        }

        override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
            // Maneja la falla de la llamada aquí
        }
    })
}

fun registrarUsuario(usuarioState: MutableState<Usuario?>) {
    val usuario = usuarioState.value
    if (usuario != null) {
        RetrofitClient.instance.registrarUsuario(usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    println("Usuario registrado con éxito")
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                // Maneja la falla de la llamada aquí
            }
        })
    }
}


fun iniciarSesion(loginUsuarioState: MutableState<LoginUsuario?>, onSuccess: (Boolean) -> Unit) {
    val usuario = loginUsuarioState.value
    if (usuario != null) {
        RetrofitClient.instance.iniciarSesion(usuario).enqueue(object : Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.isSuccessful) {
                    val matchResponse = response.body()
                    println("Respuesta del servidor: $matchResponse")
                    if (matchResponse != null && matchResponse.match == 1) {
                        // Se recibió un "1" en la respuesta JSON
                        println("Inicio de Sesión con éxito")
                        onSuccess(true)  // Llama a la función callback con `true`
                    } else {
                        println("Credenciales incorrectas login")
                        onSuccess(false)  // Llama a la función callback con `false`
                    }
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                    onSuccess(false)
                }
            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                // Manejar el error de la llamada
                println("Error en la llamada: ${t.message}")
                onSuccess(false)
            }
        })
    }
}

fun getProductos(): LiveData<List<Product>> = liveData {
    try {
        val response = RetrofitClient.instance.obtenerProductos().execute() // Asegúrate de que la llamada sea síncrona
        if (response.isSuccessful) {
            response.body()?.let { productos ->
                emit(productos) // Emitimos la lista de productos
            } ?: emit(emptyList()) // Emitimos una lista vacía si no hay productos
        } else {
            println("Error en la respuesta: ${response.errorBody()?.string()}")
            emit(emptyList()) // Emitir una lista vacía en caso de error
        }
    } catch (e: Exception) {
        println("Error en la llamada: ${e.message}")
        emit(emptyList()) // Emitir una lista vacía en caso de fallo
    }
}



fun obtenerProductos(callback: (List<Product>) -> Unit) {
    RetrofitClient.instance.obtenerProductos().enqueue(object : Callback<List<Product>> {
        override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
            if (response.isSuccessful) {
                val productos = response.body() ?: emptyList()
                callback(productos) // Pasamos la lista a través del callback
            } else {
                // Maneja el error aquí
                callback(emptyList())
            }
        }

        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
            // Maneja la falla de la llamada aquí
            callback(emptyList())
        }
    })
}


fun registrarCompra(compraState: MutableState<Compra?>) {
    val compra = compraState.value
    if (compra != null) {
        println("Compra a registrar: ${compra.curp}")
    }
    if (compra != null) {
        RetrofitClient.instance.registrarCompra(compra).enqueue(object : Callback<Compra> {
            override fun onResponse(call: Call<Compra>, response: Response<Compra>) {
                if (response.isSuccessful) {
                    println("Compra registrada con éxito")
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }
            override fun onFailure(call: Call<Compra>, t: Throwable) {
                // Maneja la falla de la llamada aquí
                println("Fallo en la llamada: ${t.message}")
            }
        })
    }
}




