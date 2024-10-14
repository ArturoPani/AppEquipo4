package mx.tec.appequipo4.model


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Retrofit para interactuar con la API.
 */

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5000" // Cambia esto a la dirección de tu servidor

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}
