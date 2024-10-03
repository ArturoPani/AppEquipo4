package mx.tec.appequipo4.model

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

fun registrarUsuario(usuario: Usuario) {
    RetrofitClient.instance.registrarUsuario(usuario).enqueue(object : Callback<Usuario> {
        override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
            if (response.isSuccessful) {
                // Maneja el usuario registrado aquí
            } else {
                // Maneja el error aquí
            }
        }

        override fun onFailure(call: Call<Usuario>, t: Throwable) {
            // Maneja la falla de la llamada aquí
        }
    })
}