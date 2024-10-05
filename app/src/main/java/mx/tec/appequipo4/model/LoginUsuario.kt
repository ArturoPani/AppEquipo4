package mx.tec.appequipo4.model

data class LoginUsuario (
    val email: String,
    val contraseña : String
)

data class MatchResponse(val match: Int)
