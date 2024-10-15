package mx.tec.appequipo4.model
import androidx.compose.runtime.MutableState
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

/**
 * Interfaz que define las operaciones de la API.
 */

interface ApiService {
    @GET("/api/obtenerUsuario")
    fun obtenerUsuarios(): Call<List<Usuario>>

    @POST("/api/registrarUsuario")
    fun registrarUsuario(@Body usuario: Usuario): Call<Usuario>

    @POST("/api/iniciarSesion")
    fun iniciarSesion(@Body usuario: LoginUsuario): Call<MatchResponse>

    @GET("api/obtenerProductos")
    fun obtenerProductos(): Call<List<Product>>

    @POST("api/obtenerProductosHistorial")
    fun obtenerProductosHistorial(@Body body: Map<String, String>): Call<List<ProductHistorial>>

    @POST("/api/registrarCompra")
    fun registrarCompra(@Body compra: Compra): Call<Compra>
}
