package mx.tec.appequipo4.model

/**
 * Clase que representa una compra.
 * @param curp CURP del usuario que realizó la compra.
 * @param products Lista de productos comprados en la compra.
 */

data class Compra(
    val curp: String,
    val products: List<Product>
)
