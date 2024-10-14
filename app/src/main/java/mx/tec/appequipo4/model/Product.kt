package mx.tec.appequipo4.model

/**
 * Clase que representa un producto en la aplicación.
 * @param product_id El identificador único del producto.
 * @param name El nombre del producto.
 * @param description La descripción del producto.
 * @param price El precio del producto.
 * @param stock_quality La cantidad en stock del producto.
 * @param category_id El identificador de la categoría a la que pertenece el producto.
 * @param created_at La fecha de creación del producto.
 * @param image_route La ruta de la imagen asociada al producto.
 */

data class Product(
    val product_id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val stock_quality: Int,
    val category_id: Int,
    val created_at: String,
    val image_route: String
)
