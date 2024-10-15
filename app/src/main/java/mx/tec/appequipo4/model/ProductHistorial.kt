package mx.tec.appequipo4.model

data class ProductHistorial(
    val order_id: Int,
    val product_id: Int,
    val name: String,
    val description: String,
    val sold_price: Double,
    val order_date: String,
    val image_route: String
)
