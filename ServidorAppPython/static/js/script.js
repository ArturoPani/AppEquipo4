// Mostrar el primer paso (email) al cargar la página
$(document).ready(function() {
    $('#step1').addClass('show');
});

// Manejar el formulario del correo electrónico
$('#emailForm').submit(function(event) {
    event.preventDefault();  // Evitar el envío normal del formulario
    let email = $('#email').val();

    if (email) {
        $('#displayEmail').text(email);  // Aquí se muestra el email ingresado
        // Ocultar el paso 1 y mostrar el paso 2
        $('#step1').removeClass('show').hide();
        $('#step2').addClass('show').show();
    } else {
        $('#errorMessage').text('Por favor, ingresa un correo válido').show();
    }
});

// Manejar el formulario de contraseña
$('#passwordForm').submit(function(event) {
    event.preventDefault();  // Evitar el envío normal del formulario
    let password = $('#password').val();
    let email = $('#email').val();  // Obtenemos el correo ingresado previamente

    // Hacer una solicitud POST al servidor Flask
    $.ajax({
        url: 'http://10.48.109.89:5000/api/iniciarSesion',  // Ruta de tu servidor Flask
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ email: email, contraseña: password }),  // Enviar los datos en formato JSON
        success: function(response) {
            if (response.match === 1) {
                // Si el login es exitoso, redirigir al panel de administrador
                window.location.href = 'admin_dashboard.html';  // Cambia esta ruta según tu estructura
            } else {
                // Mostrar mensaje de error
                $('#errorMessage').text('Credenciales incorrectas').show();
            }
        },
        error: function(error) {
            $('#errorMessage').text('Error en la conexión con el servidor').show();
        }
    });
});
