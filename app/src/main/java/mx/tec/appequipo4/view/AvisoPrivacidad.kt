package mx.tec.appequipo4.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon

@Composable
fun AvisoPrivacidadScreen(navController: NavController) {
    val backgroundColor = Color(0xFFFEE0D7)

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.popBackStack() }
            )
        }

        Text(
            text = "AVISO DE PRIVACIDAD",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = """
                En Fundación Todas Brillamos AC, valoramos la privacidad de nuestros clientes y nos comprometemos a proteger la información personal que nos proporcionan. Esta política de privacidad explica cómo recopilamos, utilizamos y protegemos sus datos personales.

                INFORMACIÓN RECOLECTADA:
                - Datos personales: nombre, dirección, correo electrónico, número de teléfono
                - Información de pago: tarjeta de crédito, débito o PayPal

                USO DE LA INFORMACIÓN:
                - Procesar y enviar pedidos
                - Enviar correos electrónicos con promociones y ofertas especiales
                - Mejorar nuestra tienda online y experiencia de usuario

                PROTECCIÓN DE LA INFORMACIÓN:
                - Utilizamos medidas de seguridad para proteger sus datos personales
                - No compartimos información personal con terceros, excepto para procesar pedidos y envíos

                DERECHOS DE LOS CLIENTES:
                - Acceder, rectificar o cancelar su información personal en cualquier momento
                - Oponerse al uso de su información para fines de marketing

                CAMBIOS EN LA POLÍTICA DE PRIVACIDAD:
                - Podemos actualizar esta política de privacidad en cualquier momento
                - Se notificará a los clientes de cualquier cambio significativo

                FECHA DE ÚLTIMA ACTUALIZACIÓN: 2 de Septiembre 2024

                Si tienes alguna pregunta o inquietud, por favor no dudes en contactarnos.
                
                Política de devolución:
                Por ser una prenda íntima y de uso personal, los cambios en este producto no son procedentes. Atendiendo a la Ley Federal de Protección al Consumidor en México (PROFECO) únicamente realizaremos cambios por defecto de fábrica. * Aplica únicamente dentro de los primeros 5 días posteriores a la entrega *
            """.trimIndent(),
            fontSize = 15.sp,
            color = Color.Black,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(40.dp))
        Spacer(modifier = Modifier.height(40.dp))
    }
}
