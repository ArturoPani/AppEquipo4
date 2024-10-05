package mx.tec.appequipo4.view


import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TiledButton(
    icon: ImageVector,
    text: String,
    edgeColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(0.8f)
            .clip(RoundedCornerShape(12))
            .padding(all = 4.dp)
            .background(edgeColor)
            .aspectRatio(1.15f)


    ) {
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(0.95f)
                .clip(CutCornerShape(1))
                .background(Color.White) //TODO: Reemplar fondo de cada boton con una imagen de fondo
                .clickable(onClick = onClick)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)

            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icono estrella",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(12.dp)
                )
                Text(
                    text = text,
                    fontSize = 24.sp,
                    color = edgeColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
        }
    }
}