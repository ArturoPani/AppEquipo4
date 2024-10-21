$(document).ready(function() {
    // Comprobar si hay pedidos pendientes y mostrar advertencia
    comprobarPedidosPendientes();

    // Funciones para cada botón
    window.administrarProductos = function() {
        window.location.href = '/product_dashboard.html';
    };

    window.administrarUsuarios = function() {
        window.location.href = '/admin_usuarios.html';
    };

    window.administrarPedidos = function() {
        window.location.href = '/admin_pedidos.html';
    };

    window.verEstadisticas = function() {
        window.location.href = '/admin_estadisticas.html';
    };
});

// Función para verificar si hay pedidos pendientes y mostrar advertencia
function comprobarPedidosPendientes() {
    $.ajax({
        url: 'http://10.48.109.89:5000/api/comprobarPedidosPendientes',  // Ruta en el servidor que verifica los pedidos pendientes
        type: 'GET',
        success: function(response) {
            if (response.pedidosPendientes > 0) {
                $('#pedidoPendiente').show();  // Mostrar mensaje de advertencia
                $('#admin-pedidos-container').css('border', '2px solid red');  // Resaltar el botón de "Administrar Pedidos"
            } else {
                $('#pedidoPendiente').hide();  // Ocultar advertencia si no hay pedidos pendientes
                $('#admin-pedidos-container').css('border', 'none');  // Remover el resalte del botón
            }
        },
        error: function(error) {
            console.log('Error al comprobar pedidos pendientes:', error);
        }
    });
}
