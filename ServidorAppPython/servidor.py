from flask import Flask, jsonify, request
import mysql.connector
from mysql.connector import Error
import hashlib
from datetime import datetime


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
        curp = data.get('curp')
        products = data.get('products')

        cursor = connection.cursor()
        print(f"curp: {curp}")
        # Obtener el user_id basado en el curp
        query_user = "SELECT user_id FROM usuario WHERE email = %s"
        cursor.execute(query_user, (curp,))
        user = cursor.fetchone()
        if user is None:
            return jsonify({'error': 'Usuario no encontrado.'}), 404

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
        print("2")
        # Registrar los detalles del pedido en 'detalles_pedido'
        for product in products:
            product_id = product.get('product_id')
         
            quantity = product.get('quantity', 1)
            
            price = product.get('price')
            
            
            

            query_detalle = "INSERT INTO detalles_pedido (order_id, product_id, quantity, sold_price) VALUES (%s, %s, %s, %s)"
            cursor.execute(query_detalle, (order_id, product_id, quantity, price))
            print("3.5")

        print("3")
        connection.commit()

        return jsonify({'message': 'Compra registrada correctamente'}), 201

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()


if __name__ == '__main__':
    #//context = ( 'cert.pem', 'key.pem')  # Ruta a tu certificado y clave privada
    #app.run(host='0.0.0.0', port=5000, ssl_context=context)
    app.run()
