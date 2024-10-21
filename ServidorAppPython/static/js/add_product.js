document.addEventListener('DOMContentLoaded', function() {
    // Cargar categorías desde el servidor
    function loadCategories() {
        fetch('http://10.48.109.89:5000/api/get_category')
            .then(response => response.json())
            .then(categories => {
                const categorySelect = document.getElementById('category');
                categories.forEach(category => {
                    const option = document.createElement('option');
                    option.value = category.category_id;
                    option.textContent = category.category_name;
                    categorySelect.appendChild(option);
                });
            });
    }

    // Mostrar campo para agregar nueva categoría
    document.getElementById('category').addEventListener('change', function() {
        if (this.value === 'new') {
            document.getElementById('new-category-container').style.display = 'block';
        } else {
            document.getElementById('new-category-container').style.display = 'none';
        }
    });

    // Actualizar la vista previa del producto
    function updatePreview() {
        const productName = document.getElementById('product-name').value;
        const description = document.getElementById('description').value;
        const price = document.getElementById('price').value;
        const stock = document.getElementById('stock-quantity').value;
        const image = document.getElementById('image').files[0];

        // Actualizar los campos de vista previa
        const previewContainer = document.getElementById('product-preview');
        previewContainer.innerHTML = `
            <h3>${productName || 'Nombre del Producto'}</h3>
            <p>${description || 'Descripción del Producto'}</p>
            <p>Precio: ${price || '0'} | Stock: ${stock || '0'}</p>
        `;

        // Vista previa de la imagen si existe
        if (image) {
            const reader = new FileReader();
            reader.onload = function(event) {
                previewContainer.innerHTML += `<img src="${event.target.result}" alt="${productName || 'Imagen del Producto'}">`;
            };
            reader.readAsDataURL(image);
        }
    }

    // Agregar producto
    document.querySelector('.add-product-btn').addEventListener('click', function() {
        const productName = document.getElementById('product-name').value;
        const description = document.getElementById('description').value;
        const price = document.getElementById('price').value;
        const stock = document.getElementById('stock-quantity').value;
        const category = document.getElementById('category').value;
        const newCategory = document.getElementById('new-category').value;
        const image = document.getElementById('image').files[0];

        if (category === 'new' && newCategory) {
            // Llamar al servidor para agregar nueva categoría
            fetch('http://10.48.109.89:5000/api/create_category', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name: newCategory })
            }).then(() => {
                // Después de agregar la nueva categoría, agregar el producto
                agregarProducto(productName, description, price, stock, newCategory, image);
            });
        } else {
            agregarProducto(productName, description, price, stock, category, image);
        }
    });

    // Función para agregar producto
    function agregarProducto(name, description, price, stock, category, image) {
        const formData = new FormData();
        formData.append('name', name);
        formData.append('description', description);
        formData.append('price', price);
        formData.append('stock_quantity', stock);
        formData.append('category_id', category);
        formData.append('image', image);

        fetch('http://10.48.109.89:5000/api/agregar_producto', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.message) {
                alert('Producto agregado con éxito.');
            } else {
                alert('Error al agregar producto.');
            }
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
            alert('Ocurrió un error al agregar el producto.');
        });
    }

    // Mostrar el nombre del archivo seleccionado
    document.getElementById('image').addEventListener('change', function() {
        const fileName = this.files[0] ? this.files[0].name : 'No se ha seleccionado ningún archivo';
        document.getElementById('file-name').textContent = fileName;
        updatePreview(); // Actualizar la vista previa cuando se seleccione una imagen
    });

    // Actualizar la vista previa cada vez que se cambie un campo
    document.getElementById('product-name').addEventListener('input', updatePreview);
    document.getElementById('description').addEventListener('input', updatePreview);
    document.getElementById('price').addEventListener('input', updatePreview);
    document.getElementById('stock-quantity').addEventListener('input', updatePreview);

    // Cargar las categorías cuando la página se carga
    loadCategories();
});
