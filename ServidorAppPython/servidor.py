from flask import Flask, jsonify, request
import mysql.connector
from mysql.connector import Error
import hashlib
from datetime import datetime
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText


#FUNCIONES
def hash_password_sha256(password: str) -> str:
    # Convertir la contraseña en bytes
    password_bytes = password.encode('utf-8')
    
    # Crear un objeto hash SHA-256
    sha256_hash = hashlib.sha256()
    
    # Actualizar el hash con los bytes de la contraseña
    sha256_hash.update(password_bytes)
    
    # Obtener el hash en formato hexadecimal
    hashed_password = sha256_hash.hexdigest()
    
    return hashed_password


def enviar_correo_confirmacion(outlook_user, outlook_password, destinatario, subject, nombre_cliente, productos, total):
    # Crear el mensaje
    msg = MIMEMultipart()
    msg['From'] = outlook_user
    msg['To'] = destinatario
    msg['Subject'] = subject
    
    # Crear el contenido del correo en formato HTML
    html = f"""
    <html>
    <body>
        <div style="font-family: Arial, sans-serif; margin: 0; padding: 0;">
            <div style="background-color: #0078D7; padding: 20px; text-align: center; color: white;">
                <h1>¡Gracias por tu compra, {nombre_cliente}!</h1>
                <h3>Fundación Todas Brillamos A.C.
                Banorte
                Cuenta: 1096319621
                Clave: 072180010963196216
                Contacto: +52 56 2808 3883 </h3>
            </div>
            <div style="padding: 20px;">
                <h2>Detalles de tu compra:</h2>
                <table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
                    <thead>
                        <tr>
                            <th style="border: 1px solid #ddd; padding: 8px;">Producto</th>
                            <th style="border: 1px solid #ddd; padding: 8px;">Cantidad</th>
                            <th style="border: 1px solid #ddd; padding: 8px;">Precio</th>
                        </tr>
                    </thead>
                    <tbody>
    """
    
    # Añadir los productos a la tabla HTML
    for producto, cantidad, precio in productos:
        html += f"""
        <tr>
            <td style="border: 1px solid #ddd; padding: 8px;">{producto}</td>
            <td style="border: 1px solid #ddd; padding: 8px;">{cantidad}</td>
            <td style="border: 1px solid #ddd; padding: 8px;">${precio:.2f}</td>
        </tr>
        """
    
    # Cerrar la tabla y añadir el total
    html += f"""
                    </tbody>
                </table>
                <h3>Total: ${total:.2f}</h3>
                <p>Gracias por confiar en Zazil. Tu pedido será procesado pronto.</p>
            </div>
            <div style="background-color: #f4f4f4; padding: 10px; text-align: center;">
                <p style="font-size: 12px; color: #666;">Este es un correo automático, no respondas a este mensaje.</p>
            </div>
        </div>
    </body>
    </html>
    """

    # Adjuntar el HTML al cuerpo del mensaje
    msg.attach(MIMEText(html, 'html'))

    # Configurar la conexión con el servidor SMTP de Outlook
    try:
        server = smtplib.SMTP('smtp.gmail.com', 587)
        server.starttls()  # Iniciar la conexión segura
        #server = smtplib.SMTP('localhost')
        server.login(outlook_user, outlook_password)  # Iniciar sesión con el correo y contraseña
        text = msg.as_string()  # Convertir el mensaje a formato string
        server.sendmail(outlook_user, destinatario, text)  # Enviar el correo
        server.quit()
        print(f"Correo enviado con éxito a {destinatario}")
    except Exception as e:
        print(f"Error al enviar el correo: {str(e)}")


##SERVIDOR
app = Flask(__name__)


# Conexión a la base de datos MySQL
def get_db_connection():
    try:
        connection = mysql.connector.connect(
            host='localhost',
            database='appmovil',
            user='root',
            password='Patapon341.'
        )
        if connection.is_connected():
            print("Conexión a la base de datos exitosa.")
        return connection
    except Error as e:
        print("Error al conectar a la base de datos:", str(e))
        return None  # Devuelve None si hay un error en la conexión

# Ruta para obtener datos de la base de datos
@app.route('/api/obtenerUsuario', methods=['GET'])
def get_data():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT * FROM usuario;")
        result = cursor.fetchall()

        return jsonify(result), 200  # Devolvemos los resultados en formato JSON

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()



@app.route('/api/registrarUsuario', methods=['POST'])
def registrarUsuario():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Obtener los datos enviados en el cuerpo de la petición
        data = request.json  # Esperamos que los datos vengan en formato JSON
        nombre = data.get('nombre')  # Reemplaza 'nombre' con el parámetro adecuado
        apellido = data.get("apellido")
        email = data.get('email')    # Reemplaza 'email' con el parámetro adecuado
        contraseña = hash_password_sha256(str(data.get("contraseña")))
        telefono = data.get("telefono")
        curp = data.get("curp")
        createdAt = data.get("creacion")




        # Inserta el usuario en la base de datos
        cursor = connection.cursor()
        query = "INSERT INTO usuario (email, password, first_name, last_name, curp, phone_number, created_at) VALUES (%s, %s, %s, %s, %s, %s, %s)"  # Asegúrate de que los nombres de las columnas coincidan con tu base de datos
        cursor.execute(query, (email, contraseña, nombre, apellido, curp, telefono, createdAt))
        connection.commit()

        return jsonify({'message': 'Usuario registrado correctamente'}), 201

    except Error as e:  
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()


@app.route('/api/iniciarSesion', methods=['POST'])
def iniciarSesion():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Obtener los datos enviados en el cuerpo de la petición
        data = request.json  # Esperamos que los datos vengan en formato JSON
        email = data.get('email')    # Reemplaza 'email' con el parámetro adecuado
        contraseña = hash_password_sha256(data.get("contraseña"))


        # Inserta el usuario en la base de datos
        cursor = connection.cursor()
        query = "SELECT COUNT(*) FROM usuario WHERE email = %s AND password = %s"
        cursor.execute(query, (email, contraseña))
        # Obtener el resultado
        result = cursor.fetchone()
        if result[0] > 0:
            # Si el resultado es mayor a 0, significa que hay una coincidencia
            return jsonify({'match': 1}), 200
        else:
            # No se encontró coincidencia
            return jsonify({'match': 0}), 200

    except Error as e:
        # En caso de error en la base de datos
        return jsonify({'error': str(e)}), 500

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()


@app.route('/api/obtenerProductos', methods=['GET'])
def obtenerProductos():
    try:
        # Establecer la conexión a la base de datos
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Crear un cursor para ejecutar consultas
        
        cursor = connection.cursor(dictionary=True)

        # Ejecutar la consulta para obtener todos los productos
        cursor.execute("SELECT * FROM producto;")
        result = cursor.fetchall()  # Obtener todos los resultados de la consulta
        # Devolver los resultados en formato JSON
        return jsonify(result), 200

    except Error as e:
        # Manejar errores de la base de datos
        return jsonify({'error': str(e)}), 500

    finally:
        # Asegurarse de cerrar el cursor y la conexión
        if connection:
            cursor.close()
            connection.close()




@app.route('/api/registrarCompra', methods=['POST'])
def registrarCompra():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Obtener los datos enviados en el cuerpo de la petición
        data = request.json  # Esperamos que los datos vengan en formato JSON
        
        # Obtener el curp del usuario
        curp = data.get('curp') #en realidad es el email
        products = data.get('products')

        cursor = connection.cursor()
        print(f"curp: {curp}")
        # Obtener el user_id basado en el curp
        query_user = "SELECT user_id FROM usuario WHERE email = %s"
        cursor.execute(query_user, (curp,))
        user = cursor.fetchone()
        if user is None:
            return jsonify({'error': 'Usuario no encontrado.'}), 404
        
        user_name_query = "SELECT first_name from usuario WHERE email = %s"
        cursor.execute(user_name_query,(curp,))
        user_name_cursor = cursor.fetchone()
        user_name = user_name_cursor[0]

        user_id = user[0]  # Extraer el user_id del resultado
        print(user_id)
        # Registrar la compra en la tabla 'pedidos'
        query_pedido = "INSERT INTO pedidos (user_id, total_amount, order_status, created_at, updated_at) VALUES (%s, %s, %s, %s, %s)"
        print("1")
        # Calcular el monto total sumando los subtotales de los productos
        total_amount = sum([product['price'] * product['stock_quality'] for product in products])
        print(total_amount)

        # Obtener las fechas actuales desde Python
        current_time = datetime.now()

        cursor.execute(query_pedido, (user_id, total_amount, 'Pendiente', current_time, current_time))
        order_id = cursor.lastrowid  # Obtener el ID del pedido recién insertado
        print(f"order id: {order_id}")
        # Registrar los detalles del pedido en 'detalles_pedido'
        productos_tupla = []  # Lista para guardar las tuplas de productos
        total_carrito = 0
        for product in products:
            product_id = product.get('product_id')
            quantity = product.get('quantity', 1)
            price = product.get('price')
            name = product.get('name')
            description = product.get('description')

            # Guardar la información en una tupla
            productos_tupla.append((name, description, price))
            
            total_carrito += price
            query_detalle = "INSERT INTO detalles_pedido (order_id, product_id, quantity, sold_price) VALUES (%s, %s, %s, %s)"
            cursor.execute(query_detalle, (order_id, product_id, quantity, price))
            

        
        connection.commit()

        return jsonify({'message': 'Compra registrada correctamente'}), 201

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            # Uso de la función
            #outlook_user = "zazil.brillamos@outlook.com"
            #outlook_password = "nfceepttbzezlben"
            outlook_user = "zazil.brillamos@gmail.com"
            outlook_password = "kres dlim lxzd vcpo"
            destinatario = curp
            subject = "Confirmación de tu compra"
            nombre_cliente = user_name
            productos = productos_tupla
            total = total_carrito

            enviar_correo_confirmacion(outlook_user, outlook_password, destinatario, subject, nombre_cliente, productos, total)



if __name__ == '__main__':
    #//context = ( 'cert.pem', 'key.pem')  # Ruta a tu certificado y clave privada
    #app.run(host='0.0.0.0', port=5000, ssl_context=context)
    #app.run()
    app.run(host='0.0.0.0', port=5000)

