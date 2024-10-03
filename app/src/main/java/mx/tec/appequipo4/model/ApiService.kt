package mx.tec.appequipo4.model
import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {
    @GET("/api/obtenerUsuario")
    fun obtenerUsuarios(): Call<List<Usuario>>

    @POST("/api/registrarUsuario")
    fun registrarUsuario(@Body usuario: Usuario): Call<Usuario>
}
