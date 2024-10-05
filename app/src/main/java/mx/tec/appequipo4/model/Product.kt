package mx.tec.appequipo4.model

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
