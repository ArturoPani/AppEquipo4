package mx.tec.appequipo4.model

/**
 * Clase de datos que representa un usuario.
 * @param nombre El nombre del usuario.
 * @param apellido El apellido del usuario.
 * @param email El correo electrónico del usuario.
 * @param contraseña La contraseña del usuario.
 * @param telefono El número de teléfono del usuario.
 * @param edad La edad del usuario.
 * @param curp El CURP del usuario.
 * @param creacion La fecha de creación del usuario.
 */

data class Usuario (
    val nombre: String,
    val apellido: String,
    val email: String,
    val contraseña : String,
    val telefono : String,
    val edad : String,
    val curp : String,
    val creacion : String
)