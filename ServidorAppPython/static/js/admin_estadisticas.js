// Datos de prueba para ventas y productos vendidos
const dataVentasTotales = [1500, 3200, 4500, 2000, 3900, 5600];
const dataProductosVendidos = [100, 150, 300, 120, 200, 400];

const labelsMeses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio'];


$(document).ready(function() {
    const fecha_inicio = '2024-01-01';
    const fecha_fin = '2024-12-31';

    // Función para obtener las ventas totales
    $.ajax({
        url: `http://10.48.109.89:5000/api/ventasTotales?fecha_inicio=${fecha_inicio}&fecha_fin=${fecha_fin}`,
        method: 'GET',
        success: function(data) {
            $('#ventas-totales').text(`Ventas Totales: $${data.ventas_totales}`);
        },
        error: function(err) {
            console.log("Error al obtener ventas totales:", err);
        }
    });

    // Función para obtener el total de productos vendidos
    $.ajax({
        url: `http://10.48.109.89:5000/api/totalProductosVendidos?fecha_inicio=${fecha_inicio}&fecha_fin=${fecha_fin}`,
        method: 'GET',
        success: function(data) {
            $('#productos-vendidos').text(`Productos Vendidos: ${data.total_productos_vendidos}`);
        },
        error: function(err) {
            console.log("Error al obtener productos vendidos:", err);
        }
    });

    // Función para obtener el producto más vendido por fechas
    $.ajax({
        url: `http://10.48.109.89:5000/api/productoMasVendidoPorFechas?fecha_inicio=${fecha_inicio}&fecha_fin=${fecha_fin}`,
        method: 'GET',
        success: function(data) {
            $('#producto-mas-vendido').text(`Producto Más Vendido: ${data.producto} (${data.total_vendido} vendidos)`);
        },
        error: function(err) {
            console.log("Error al obtener el producto más vendido:", err);
        }
    });

    // Función para obtener el producto más vendido en general
    $.ajax({
        url: 'http://10.48.109.89:5000/api/productoMasVendidoGeneral',
        method: 'GET',
        success: function(data) {
            $('#producto-mas-vendido-general').text(`Producto Más Vendido (General): ${data.name} (${data.total_vendido} vendidos)`);
        },
        error: function(err) {
            console.log("Error al obtener el producto más vendido (general):", err);
        }
    });

    // Función para obtener el cliente con más pedidos
    $.ajax({
        url: 'http://10.48.109.89:5000/api/clienteMasPedidos',
        method: 'GET',
        success: function(data) {
            $('#cliente-mas-pedidos').text(`Cliente con Más Pedidos: ${data.cliente} (${data.total_pedidos} pedidos)`);
        },
        error: function(err) {
            console.log("Error al obtener el cliente con más pedidos:", err);
        }
    });

    // Función para obtener el total de productos por categoría
    $.ajax({
        url: 'http://10.48.109.89:5000/api/totalProductosPorCategoria',
        method: 'GET',
        success: function(data) {
            let categoriaText = '';
            data.forEach(categoria => {
                categoriaText += `${categoria.categoria}: ${categoria.total_vendidos} vendidos\n`;
            });
            $('#productos-por-categoria').text(`Productos por Categoría:\n${categoriaText}`);
        },
        error: function(err) {
            console.log("Error al obtener los productos por categoría:", err);
        }
    });

    // Función para obtener usuarios registrados por mes
    $.ajax({
        url: 'http://10.48.109.89:5000/api/usuariosRegistradosPorMes',
        method: 'GET',
        success: function(data) {
            let usuarioText = '';
            data.forEach(usuario => {
                usuarioText += `${usuario.mes}: ${usuario.total_registrados} usuarios registrados\n`;
            });
            $('#usuarios-registrados').text(`Usuarios Registrados por Mes:\n${usuarioText}`);
        },
        error: function(err) {
            console.log("Error al obtener los usuarios registrados por mes:", err);
        }
    });

    // Función para obtener el producto más vendido por cantidad
    $.ajax({
        url: 'http://10.48.109.89:5000/api/productoMasVendidoCantidad',
        method: 'GET',
        success: function(data) {
            $('#producto-mas-vendido-cantidad').text(`Producto Más Vendido (Cantidad): ${data.producto} (${data.cantidad_vendida} vendidos)`);
        },
        error: function(err) {
            console.log("Error al obtener el producto más vendido (cantidad):", err);
        }
    });

    // Función para obtener los ingresos totales
    $.ajax({
        url: 'http://10.48.109.89:5000/api/ingresosTotales',
        method: 'GET',
        success: function(data) {
            $('#ingresos-totales').text(`Ingresos Totales: $${data.ingresos_totales}`);
        },
        error: function(err) {
            console.log("Error al obtener los ingresos totales:", err);
        }
    });
});

// Crear gráfico de Ventas Totales
const ctxVentasTotales = document.getElementById('ventasTotalesChart').getContext('2d');
const ventasTotalesChart = new Chart(ctxVentasTotales, {
    type: 'line',  // Tipo de gráfico
    data: {
        labels: labelsMeses,
        datasets: [{
            label: 'Ventas Totales ($)',
            data: dataVentasTotales,
            backgroundColor: 'rgba(255, 87, 34, 0.2)',
            borderColor: 'rgba(255, 87, 34, 1)',
            borderWidth: 2
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
        }
    }
});

// Crear gráfico de Productos Vendidos
const ctxProductosVendidos = document.getElementById('productosVendidosChart').getContext('2d');
const productosVendidosChart = new Chart(ctxProductosVendidos, {
    type: 'bar',  // Tipo de gráfico
    data: {
        labels: labelsMeses,
        datasets: [{
            label: 'Productos Vendidos',
            data: dataProductosVendidos,
            backgroundColor: 'rgba(33, 150, 243, 0.2)',
            borderColor: 'rgba(33, 150, 243, 1)',
            borderWidth: 2
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
        }
    }
});
