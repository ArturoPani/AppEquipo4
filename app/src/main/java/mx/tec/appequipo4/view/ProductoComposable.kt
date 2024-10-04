package mx.tec.appequipo4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mx.tec.appequipo4.model.Product

@Composable
fun ProductoComposable(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Imagen del producto
            Image(
                painter = rememberAsyncImagePainter(model = product.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            // Detalles del producto
            Column(modifier = Modifier.weight(2f)) {
                Text(text = product.name, color = Color.Black)
                Text(text = "\$${product.price}", color = Color.Gray)
            }
        }
    }
}
