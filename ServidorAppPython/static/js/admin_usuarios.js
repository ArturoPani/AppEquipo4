$(document).ready(function() {
    // Función para cargar la lista de usuarios
    function loadUsuarios() {
        $.ajax({
            url: 'http://10.48.109.89:5000/api/obtenerUsuarios',
            method: 'GET',
            success: function(usuarios) {
                // Limpiar la tabla antes de agregar usuarios
                $('#tabla-usuarios tbody').empty();

                // Agregar usuarios a la tabla
                usuarios.forEach(usuario => {
                    $('#tabla-usuarios tbody').append(`
                        <tr>
                            <td>${usuario.first_name} ${usuario.last_name}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.total_pedidos}</td>
                            <td>
                                <button class="ver-pedidos-btn" data-id="${usuario.user_id}">Ver Pedidos</button>
                            </td>
                        </tr>
                    `);
                });

                // Asignar el evento click a los botones "Ver Pedidos"
                $('.ver-pedidos-btn').on('click', function() {
                    const userId = $(this).data('id');
                    mostrarPedidosUsuario(userId);
                });
            },
            error: function(err) {
                showAlert('Error al cargar los usuarios', 'error');
            }
        });
    }

    // Función para mostrar los pedidos de un usuario
    function mostrarPedidosUsuario(userId) {
        $.ajax({
            url: `http://10.48.109.89:5000/api/obtenerPedidosUsuario/${userId}`,
            method: 'GET',
            success: function(pedidos) {
                // Mostrar los pedidos en una tabla
                $('#tabla-pedidos tbody').empty();
    
                pedidos.forEach(pedido => {
                    // La respuesta ya contiene 'productos_comprados' como una cadena
                    let productosList = pedido.productos_comprados;
    
                    $('#tabla-pedidos tbody').append(`
                        <tr>
                            <td>${new Date(pedido.fecha_pedido).toLocaleDateString()}</td>
                            <td>${pedido.order_status}</td>
                            <td>${pedido.total_productos}</td>
                            <td>${productosList}</td> <!-- Mostrar productos con categoría -->
                        </tr>
                    `);
                });
    
                $('#modal-pedidos').fadeIn();
            },
            error: function(err) {
                showAlert('Error al obtener los pedidos del usuario', 'error');
            }
        });
    }
    
    
    

    // Cargar los usuarios al iniciar la página
    loadUsuarios();

    // Cerrar el modal de pedidos
    $('#close-modal').on('click', function() {
        $('#modal-pedidos').fadeOut();
    });
});


function showAlert(message, type = 'success') {
    const alertDiv = document.createElement('div');
    alertDiv.classList.add('alert', `alert-${type}`);
    alertDiv.innerText = message;

    // Agregar la alerta al cuerpo o a un contenedor específico
    document.body.appendChild(alertDiv);

    // Ocultar la alerta después de 5 segundos
    setTimeout(() => {
        alertDiv.style.opacity = '0';
        setTimeout(() => alertDiv.remove(), 500);  // Esperar a que la transición de opacidad termine
    }, 5000);
}