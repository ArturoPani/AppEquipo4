package mx.tec.appequipo4.model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path

interface ApiService {
    @GET("/api/obtenerUsuario")
    fun obtenerUsuarios(): Call<List<Usuario>>

    @POST("/api/registarUsuario")
    fun registrarUsuario(@Body usuario: Usuario):Call<Usuario>
}