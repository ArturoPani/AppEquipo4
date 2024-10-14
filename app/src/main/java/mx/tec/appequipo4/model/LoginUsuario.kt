package mx.tec.appequipo4.model

/**
 * Clase que representa una compra.
 * @param email El correo electrónico del usuario que realizó la compra.
 * @param contraseña La contraseña del usuario que realizó la compra.
 */

data class LoginUsuario (
    val email: String,
    val contraseña : String
)

data class MatchResponse(val match: Int)
