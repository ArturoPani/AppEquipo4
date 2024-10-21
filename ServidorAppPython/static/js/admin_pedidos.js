$(document).ready(function() {
    // Función para cargar pedidos desde el servidor
    function loadPedidos() {
        $.ajax({
            url: 'http://10.48.109.89:5000/api/obtenerPedidos',  // Cambia la URL según la de tu servidor
            method: 'GET',
            success: function(pedidos) {
                // Limpiar la tabla antes de agregar los pedidos
                $('#tabla-pedidos tbody').empty();

                // Agregar cada pedido a la tabla
                pedidos.forEach(pedido => {
                    $('#tabla-pedidos tbody').append(`
                        <tr>
                            <td>${pedido.cliente}</td>
                            <td>${new Date(pedido.fecha_pedido).toLocaleDateString()}</td>
                            <td>${pedido.estado}</td>
                            <td>$${pedido.total}</td>
                            <td>${pedido.metodo_pago}</td>
                            <td>
                                <button class="detalle-btn" data-id="${pedido.order_id}">Ver Detalles</button>
                                <br>
                                <button class="estado-btn" data-id="${pedido.order_id}">Cambiar Estado</button>
                                <br>
                                <select class="estado-select" data-id="${pedido.order_id}" style="display:none;">
                                    <option value="pendiente" ${pedido.estado === 'pendiente' ? 'selected' : ''}>Pendiente</option>
                                    <option value="en_proceso" ${pedido.estado === 'en_proceso' ? 'selected' : ''}>En Proceso</option>
                                    <option value="completado" ${pedido.estado === 'completado' ? 'selected' : ''}>Completado</option>
                                    <option value="cancelado" ${pedido.estado === 'cancelado' ? 'selected' : ''}>Cancelado</option>
                                </select>
                            </td>
                        </tr>
                    `);
                });

                // Asignar el evento click a los botones de "Ver Detalles"
                $('.detalle-btn').on('click', function() {
                    const pedidoId = $(this).data('id');
                    mostrarDetallesPedido(pedidoId);
                });

                // Asignar el evento click al botón "Cambiar Estado"
                $('.estado-btn').on('click', function() {
                    const pedidoId = $(this).data('id');
                    $(`.estado-select[data-id='${pedidoId}']`).toggle(); // Mostrar/Ocultar el select
                });

                // Asignar el evento de cambio al select de estado
                $('.estado-select').on('change', function() {
                    const pedidoId = $(this).data('id');
                    const nuevoEstado = $(this).val();
                    cambiarEstadoPedido(pedidoId, nuevoEstado);
                });
            },
            error: function(err) {
                alert('Error al cargar los pedidos.');
            }
        });
    }

    // Función para cambiar el estado del pedido en el servidor
    function cambiarEstadoPedido(pedidoId, nuevoEstado) {
        $.ajax({
            url: `http://10.48.109.89:5000/api/cambiarEstadoPedido/${pedidoId}`,  // Cambia la URL según la de tu servidor
            method: 'PUT',
            data: JSON.stringify({ estado: nuevoEstado }),
            contentType: 'application/json',
            success: function(response) {
                showAlert('Pedido actualizado correctamente', 'success');
                loadPedidos(); // Recargar los pedidos
            },
            error: function(err) {
                alert('Error al actualizar el estado del pedido.');
            }
        });
    }

    // Función para mostrar los detalles de un pedido en el modal
    function mostrarDetallesPedido(pedidoId) {
        $.ajax({
            url: `http://10.48.109.89:5000/api/detallesPedido/${pedidoId}`,  // Cambia la URL según la de tu servidor
            method: 'GET',
            success: function(pedido) {
                // Llenar los campos del modal con los detalles del pedido
                $('#detalle-cliente').val(pedido.cliente);
                $('#detalle-fecha').val(new Date(pedido.fecha_pedido).toLocaleDateString());
                $('#detalle-total').val(`$${pedido.total}`);
                $('#detalle-metodo').val(pedido.metodo_pago);

                // Limpiar y agregar los productos del pedido
                $('#detalle-productos').empty();
                pedido.productos.forEach(producto => {
                    $('#detalle-productos').append(`
                        <div>
                            <p>${producto.name} (Cantidad: ${producto.quantity}, Precio Unitario: $${producto.precio_unitario})</p>
                        </div>
                    `);
                });

                // Mostrar el modal
                $('.overlay').fadeIn();
                $('.modal').fadeIn();
            },
            error: function(err) {
                alert('Error al obtener los detalles del pedido.');
            }
        });
    }

    // Cerrar el modal al hacer clic en el botón "Cerrar"
    $('#close-modal').on('click', function() {
        $('.overlay').fadeOut();
        $('.modal').fadeOut();
    });

    // Cargar los pedidos al iniciar la página
    loadPedidos();
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