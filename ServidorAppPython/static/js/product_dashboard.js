$(document).ready(function() {
    // Cargar productos desde el servidor
    function loadProducts() {
        $.ajax({
            url: 'http://10.48.109.89:5000/api/obtenerProductosWeb',
            method: 'GET',
            success: function(products) {
                $('#product-list').empty();  // Limpiar la lista antes de agregar los productos

                products.forEach(product => {
                    $('#product-list').append(`
                        <div class="product-item" data-id="${product.product_id}" data-name="${product.name.toLowerCase()}">
                            <img src="http://10.48.109.89:5000/static/images/${product.image_route}.png" alt="${product.name}">
                            <div class="product-details">
                                <h3>${product.name} - ${product.category_name}</h3>
                                <p>${product.description}</p>
                                <p>Precio: $${product.price} | Stock: ${product.stock_quantity}</p>
                                <p>ID: ${product.product_id} | Creado: ${product.created_at}</p>
                            </div>
                            <button class="edit-btn">Editar</button>
                        </div>
                    `);
                });

                // Manejar el clic en un producto para mostrar el modal de edición
                $('.edit-btn').on('click', function() {
                    const productId = $(this).closest('.product-item').data('id');
                    const productName = $(this).closest('.product-item').find('h3').text().split(' - ')[0];
                    const productDescription = $(this).closest('.product-item').find('p').first().text();
                    const productPrice = $(this).closest('.product-item').find('p').eq(1).text().split(': $')[1];
                    const productStock = $(this).closest('.product-item').find('p').eq(1).text().split('| Stock: ')[1];
                    const productImage = $(this).closest('.product-item').find('img').attr('src');

                    // Llenar los campos del modal con los valores actuales del producto
                    $('#edit-name').val(productName);
                    $('#edit-description').val(productDescription);
                    $('#edit-price').val(productPrice);
                    $('#edit-stock').val(productStock);
                    $('#product-preview img').attr('src', productImage);  // Mostrar la imagen actual en la vista previa

                    // Mostrar el modal
                    $('.modal').fadeIn();
                    $('.overlay').fadeIn();

                    // Guardar el id del producto para usarlo al guardar los cambios
                    $('#save-btn').data('id', productId);
                });

                // Manejar el clic en "Guardar" para enviar los cambios al servidor
                $('#save-btn').on('click', function() {
                    const productId = $(this).data('id');
                    const name = $('#edit-name').val();
                    const description = $('#edit-description').val();
                    const price = $('#edit-price').val();
                    const stock = $('#edit-stock').val();
                    const image = $('#edit-image')[0].files[0];  // Obtener la nueva imagen seleccionada (si existe)

                    // Validar que todos los campos estén llenos
                    if (!name || !description || !price || !stock) {
                        showAlert('Error al actualizar el producto. Asegurate de llenar todos los campos', 'error');
                        return;  // Detener la ejecución si algún campo está vacío
                    }

                    // Crear FormData para enviar imagen y datos
                    const formData = new FormData();
                    formData.append('name', name);
                    formData.append('description', description);
                    formData.append('price', price);
                    formData.append('stock_quantity', stock);

                    if (image) {
                        formData.append('image', image);  // Solo enviar la nueva imagen si se selecciona
                    }

                    // Enviar los datos actualizados al servidor
                    $.ajax({
                        url: `http://10.48.109.89:5000/api/editarProducto/${productId}`,
                        method: 'POST',
                        processData: false,  // Importante para FormData
                        contentType: false,  // Importante para FormData
                        data: formData,
                        success: function(response) {
                            showAlert('Producto actualizado con éxito.', 'success');
                            // Opcional: recargar los productos para mostrar los cambios
                            loadProducts();
                        },
                        error: function(err) {
                            showAlert('Error al actualizar el producto.', 'error');
                        }
                    });

                    // Cerrar el modal
                    $('.modal').fadeOut();
                    $('.overlay').fadeOut();
                });

                // Manejar el clic en "Cancelar" o fuera del modal para cerrar
                $('#cancel-btn, .overlay').on('click', function() {
                    $('.modal').fadeOut();
                    $('.overlay').fadeOut();
                });

                // Actualizar la vista previa de la imagen seleccionada
                $('#edit-image').on('change', function() {
                    const file = this.files[0];
                    if (file) {
                        const reader = new FileReader();
                        reader.onload = function(event) {
                            $('#product-preview img').attr('src', event.target.result);  // Mostrar vista previa de la nueva imagen
                        };
                        reader.readAsDataURL(file);
                    }
                });
            }
        });
    }

    // Cargar productos al iniciar la página
    loadProducts();
    
    $('#search-bar').on('input', function() {
        const searchTerm = $(this).val().toLowerCase();
    
        $('.product-item').each(function() {
            const productName = $(this).data('name') || '';  // Asegurarse de que sea una cadena
            if (productName.includes(searchTerm)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });

    $('#add-product-btn').click(function() {
        window.location.href = 'add_product.html';
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