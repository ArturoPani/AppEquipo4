from flask import Flask, jsonify, request
import mysql.connector
from mysql.connector import Error
import hashlib
from datetime import datetime
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from flask import Flask, jsonify, request
import mysql.connector
from mysql.connector import Error
import hashlib
from datetime import datetime
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from flask import Flask, render_template
from flask_cors import CORS
from werkzeug.utils import secure_filename
import os


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



def enviar_correo_bienvenida(outlook_user, outlook_password, destinatario, subject, nombre_cliente):
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
                <h1>¡Bienvenida/o a Zazil, {nombre_cliente}!</h1>
                <p>Estamos muy emocionados de tenerte en nuestra comunidad.</p>
            </div>
            <div style="padding: 20px;">
                <h2>¡Explora nuestra tienda en línea y encuentra productos increíbles!</h2>
                <p>Desde ahora, podrás disfrutar de una experiencia de compra fácil, rápida y segura. Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos.</p>
                <p>Nos encantaría ayudarte en cada paso del camino.</p>
            </div>
            <div style="background-color: #f4f4f4; padding: 10px; text-align: center;">
                <p>Contacto: +52 56 2808 3883</p>
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
        server.login(outlook_user, outlook_password)  # Iniciar sesión con el correo y contraseña
        text = msg.as_string()  # Convertir el mensaje a formato string
        server.sendmail(outlook_user, destinatario, text)  # Enviar el correo
        server.quit()
        print(f"Correo de bienvenida enviado con éxito a {destinatario}")
    except Exception as e:
        print(f"Error al enviar el correo: {str(e)}")



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
                Clabe: 072180010963196216
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



#---------------------------------------------------------------------------------------------------------------------------
##SERVIDOR
#app = Flask(__name__)
app = Flask(__name__, static_folder='static', template_folder='.')

# Conexión a la base de datos MySQL
def get_db_connection():
    try:
        connection = mysql.connector.connect(
            host='localhost',
            database='appzazil',
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
            outlook_user = "zazil.brillamos@gmail.com"
            outlook_password = "kres dlim lxzd vcpo"
            destinatario = email
            subject = "¡Bienvenida/o a Zazil!"
            nombre_cliente = nombre
            enviar_correo_bienvenida(outlook_user,outlook_password,destinatario,subject,nombre_cliente)

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




#me queda probarlo en postman
@app.route('/api/obtenerProductosHistorial', methods=['POST'])
def obtenerProductosHistorial():
    try:
        # Establecer la conexión a la base de datos
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Crear un cursor para ejecutar consultas
        cursor = connection.cursor(dictionary=True)

        # Obtener los datos enviados en el cuerpo de la petición
        data = request.json  # Esperamos que los datos vengan en formato JSON
        print(data)
        # Obtener el email del usuario (en realidad es el email, no el curp)
        email = data.get('email')  # Asegúrate de que el frontend envía el 'email'
        
        if not email:
            return jsonify({'error': 'Email no proporcionado.'}), 400

        # Ejecutar la consulta para obtener los productos del historial de compras del usuario
        query = '''
        SELECT 
            p.order_id, 
            d.product_id, 
            prod.name AS name, 
            prod.description,
            d.sold_price, 
            p.created_at AS order_date,
            prod.image_route
        FROM 
            usuario u
        JOIN 
            pedidos p ON u.user_id = p.user_id
        JOIN 
            detalles_pedido d ON p.order_id = d.order_id
        JOIN 
            producto prod ON d.product_id = prod.product_id
        WHERE 
            u.email = %s;
        '''

        # Ejecutar la consulta con el email proporcionado
        cursor.execute(query, (email,))
        result = cursor.fetchall()  # Obtener todos los resultados de la consulta

        # Si no se encuentran resultados
        if not result:
            return jsonify({'message': 'No se encontraron productos para este cliente.'}), 404

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
        total_amount = sum([product['price'] * product['stock_quantity'] for product in products])
        print(total_amount)


        

        # Obtener las fechas actuales desde Python
        current_time = datetime.now()

        cursor.execute(query_pedido, (user_id, total_amount, 'Pendiente', current_time, current_time))
        order_id = cursor.lastrowid  # Obtener el ID del pedido recién insertado
        print(f"order id: {order_id}")


        query_pago = "INSERT INTO metodo_de_pago (order_id,payment_type,payment_status,created_at) VALUES (%s,%s,%s,%s)"
        cursor.execute(query_pago,(order_id,"transferencia","pendiente",current_time))
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

#--------------------------------------------------------------------------------------------------------------------------------
#SERVIDOR PARA PAGINA WEB

CORS(app)  # Habilitar CORS para toda la aplicación

UPLOAD_FOLDER = 'static/images'
ALLOWED_EXTENSIONS = {'png'}

app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/api/IniciarSesionWeb', methods=['POST'])
def iniciarSesionWeb():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Obtener los datos enviados en la petición
        data = request.json
        email = data.get('email')
        contraseña = hash_password_sha256(data.get("contraseña"))

        # Consultar la base de datos para verificar el usuario
        cursor = connection.cursor()
        query = "SELECT user_id FROM usuario WHERE email = %s AND password = %s"
        cursor.execute(query, (email, contraseña))
        result = cursor.fetchone()

        # Validar si el usuario existe
        if result:
            return jsonify({'match': 1}), 200  # Login exitoso
        else:
            return jsonify({'match': 0}), 200  # Credenciales incorrectas

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()


@app.route('/api/obtenerProductosWeb', methods=['GET'])
def obtener_productosWeb():
    try:
        # Establecer la conexión con MySQL usando mysql.connector
        conn = get_db_connection()
        cursor = conn.cursor()

        # Ejecutar la consulta para obtener los productos junto con el nombre de la categoría
        query = """
            SELECT 
                p.product_id, 
                p.name, 
                p.description, 
                p.price, 
                p.stock_quantity, 
                c.category_name,  -- Obtener el nombre de la categoría
                p.created_at, 
                p.image_route
            FROM 
                producto p
            JOIN 
                categoria c ON p.category_id = c.category_id;
        """
        cursor.execute(query)
        productos = cursor.fetchall()

        # Convertir los resultados a un diccionario
        productos_dict = []
        for producto in productos:
            productos_dict.append({
                'product_id': producto[0],
                'name': producto[1],
                'description': producto[2],
                'price': producto[3],
                'stock_quantity': producto[4],
                'category_name': producto[5],  # Devolver el nombre de la categoría
                'created_at': str(producto[6]),
                'image_route': producto[7]  # Ruta de la imagen del producto
            })

        cursor.close()
        conn.close()

        # Devolver los productos como JSON
        return jsonify(productos_dict)

    except mysql.connector.Error as err:
        print(f"Error: {err}")
        return jsonify({'error': 'Error al obtener productos.'}), 500

@app.route('/api/get_category', methods=['GET'])
def get_category():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT category_id, category_name FROM categoria")
        categories = cursor.fetchall()

        return jsonify(categories), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/create_category', methods=['POST'])
def create_category():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Obtener el nombre de la categoría enviada en la solicitud
        data = request.json
        new_category = data.get('name')

        if not new_category:
            return jsonify({'error': 'El nombre de la categoría es obligatorio.'}), 400

        # Insertar la nueva categoría en la base de datos
        cursor = connection.cursor()
        query = "INSERT INTO categoria (category_name) VALUES (%s)"
        cursor.execute(query, (new_category,))
        connection.commit()

        return jsonify({'message': 'Categoría creada con éxito.'}), 201

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/api/agregar_producto', methods=['POST'])
def agregar_producto():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Obtener los datos del producto
        name = request.form.get('name')
        description = request.form.get('description')
        price = request.form.get('price')
        stock_quantity = request.form.get('stock_quantity')
        category_id = request.form.get('category_id')
        image = request.files.get('image')

        # Agregar logs de depuración para verificar los valores
        print(f'Nombre del producto: {name}')
        print(f'Descripción: {description}')
        print(f'Precio: {price}')
        print(f'Stock: {stock_quantity}')
        print(f'ID de categoría: {category_id}')
        print(f'Imagen: {image}')

        # Verificar si todos los campos requeridos fueron enviados
        if not all([name, description, price, stock_quantity, category_id, image]):
            return jsonify({'error': 'Todos los campos son obligatorios.'}), 400

        # Verificar que el archivo tenga una extensión permitida
        if not allowed_file(image.filename):
            return jsonify({'error': 'Solo se permiten archivos PNG.'}), 400

        # Guardar el archivo de imagen
        filename = secure_filename(image.filename)
        filename_without_extension = os.path.splitext(filename)[0]  # Eliminar la extensión
        image_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        image.save(image_path)

        # Guardar la ruta de la imagen sin la extensión en la base de datos
        image_route = f'{filename_without_extension}'

        # Insertar los datos del producto en la base de datos
        cursor = connection.cursor()
        query = """
            INSERT INTO producto (name, description, price, stock_quantity, category_id, image_route, created_at)
            VALUES (%s, %s, %s, %s, %s, %s, NOW())
        """
        cursor.execute(query, (name, description, price, stock_quantity, category_id, image_route))
        connection.commit()

        return jsonify({'message': 'Producto agregado con éxito.'}), 201

    except Error as e:
        print(f'Error al agregar producto: {e}')  # Agregar log del error
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/editarProducto/<int:product_id>', methods=['POST'])
def editar_producto(product_id):
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        # Obtener los datos enviados en la solicitud
        name = request.form.get('name')
        description = request.form.get('description')
        price = request.form.get('price')
        stock_quantity = request.form.get('stock_quantity')
        image = request.files.get('image')  # Obtener la nueva imagen si fue seleccionada

        # Verificar si todos los campos requeridos están presentes
        if not all([name, description, price, stock_quantity]):
            return jsonify({'error': 'Todos los campos de producto son obligatorios.'}), 400

        cursor = connection.cursor()

        # Si se ha enviado una nueva imagen, manejar la actualización de la imagen
        if image and allowed_file(image.filename):
            filename = secure_filename(image.filename)
            filename_without_extension = filename.rsplit('.', 1)[0]  # Eliminar la extensión ".png"
            image_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
            image.save(image_path)

            # Eliminar la imagen anterior si no es usada por otros productos
            cursor.execute("SELECT image_route FROM producto WHERE product_id = %s", (product_id,))
            old_image = cursor.fetchone()

            if old_image and old_image[0] != filename_without_extension:  # Comparar sin la extensión
                cursor.execute("SELECT COUNT(*) FROM producto WHERE image_route = %s", (old_image[0],))
                if cursor.fetchone()[0] == 1:
                    old_image_path = os.path.join(app.config['UPLOAD_FOLDER'], os.path.basename(old_image[0]))
                    if os.path.exists(old_image_path):
                        os.remove(old_image_path)

            # Actualizar los datos del producto incluyendo la nueva imagen
            image_route = f'static/images/{filename_without_extension}'  # Guardar la ruta completa
            query = """
                UPDATE producto 
                SET name = %s, description = %s, price = %s, stock_quantity = %s, image_route = %s
                WHERE product_id = %s
            """
            cursor.execute(query, (name, description, price, stock_quantity, image_route, product_id))

        else:
            # Si no se envió una nueva imagen, solo actualizar los demás campos
            query = """
                UPDATE producto 
                SET name = %s, description = %s, price = %s, stock_quantity = %s
                WHERE product_id = %s
            """
            cursor.execute(query, (name, description, price, stock_quantity, product_id))

        connection.commit()
        return jsonify({'message': 'Producto actualizado con éxito.'}), 200

    except Error as e:
        print(f'Error al actualizar producto: {e}')  # Agregar log del error
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

#Funciones para administrar pedidos ------------------------

@app.route('/api/obtenerPedidos', methods=['GET'])
def obtener_pedidos():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        query = """
            SELECT p.order_id, u.first_name as cliente, p.created_at as fecha_pedido, p.order_status as estado, p.total_amount as total, pm.payment_type as metodo_pago
            FROM pedidos p
            JOIN usuario u ON p.user_id = u.user_id
            JOIN metodo_de_pago pm ON p.order_id = pm.order_id
        """
        cursor.execute(query)
        pedidos = cursor.fetchall()

        return jsonify(pedidos), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()


@app.route('/api/detallesPedido/<int:pedido_id>', methods=['GET'])
def detalles_pedido(pedido_id):
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        # Obtener detalles básicos del pedido (cambiando p.created_at y p.order_id según la estructura correcta)
        query = """
            SELECT p.order_id, u.first_name AS cliente, p.created_at AS fecha_pedido, 
                   p.order_status AS estado, p.total_amount AS total, 
                   pm.payment_type AS metodo_pago 
            FROM pedidos p
            JOIN usuario u ON p.user_id = u.user_id
            JOIN metodo_de_pago pm ON p.order_id = pm.order_id
            WHERE p.order_id = %s
        """
        cursor.execute(query, (pedido_id,))
        pedido = cursor.fetchone()

        if not pedido:
            return jsonify({'error': 'Pedido no encontrado.'}), 404

        # Obtener los productos del pedido
        query_productos = """
            SELECT pr.name, dp.quantity, dp.sold_price AS precio_unitario
            FROM detalles_pedido dp
            JOIN producto pr ON dp.product_id = pr.product_id
            WHERE dp.order_id = %s
        """
        cursor.execute(query_productos, (pedido_id,))
        productos = cursor.fetchall()

        pedido['productos'] = productos

        return jsonify(pedido), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/cambiarEstadoPedido/<int:pedido_id>', methods=['PUT'])
def cambiar_estado_pedido(pedido_id):
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        nuevo_estado = request.json.get('estado')

        cursor = connection.cursor()
        query = """
            UPDATE pedidos
            SET order_status = %s, updated_at = NOW()
            WHERE order_id = %s
        """
        cursor.execute(query, (nuevo_estado, pedido_id))
        connection.commit()

        return jsonify({'message': 'Estado actualizado correctamente.'}), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/comprobarPedidosPendientes', methods=['GET'])
def comprobar_pedidos_pendientes():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor()
        query = """
            SELECT COUNT(*) as pedidosPendientes
            FROM pedidos
            WHERE order_status = 'pendiente'
        """
        cursor.execute(query)
        result = cursor.fetchone()
        pedidosPendientes = result[0]

        return jsonify({'pedidosPendientes': pedidosPendientes}), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/obtenerUsuarios', methods=['GET'])
def obtener_usuarios():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        query = """
            SELECT u.user_id, u.first_name, u.last_name, u.email, COUNT(p.order_id) as total_pedidos
            FROM usuario u
            LEFT JOIN pedidos p ON u.user_id = p.user_id
            GROUP BY u.user_id
        """
        cursor.execute(query)
        usuarios = cursor.fetchall()

        return jsonify(usuarios), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/obtenerPedidosUsuario/<int:user_id>', methods=['GET'])
def obtener_pedidos_usuario(user_id):
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        # Obtener los pedidos y productos comprados
        query = """
            SELECT p.order_id, p.created_at as fecha_pedido, p.order_status, 
                SUM(dp.quantity) as total_productos, 
                GROUP_CONCAT(CONCAT(pr.name, ' (', c.category_name, ')') ORDER BY pr.name) as productos_comprados
            FROM pedidos p
            JOIN detalles_pedido dp ON p.order_id = dp.order_id
            JOIN producto pr ON dp.product_id = pr.product_id
            JOIN categoria c ON pr.category_id = c.category_id
            WHERE p.user_id = %s
            GROUP BY p.order_id;
        """
        cursor.execute(query, (user_id,))
        pedidos = cursor.fetchall()

        return jsonify(pedidos), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/ventasTotales', methods=['GET'])
def ventas_totales():
    try:
        # Obtener los parámetros de fecha de la solicitud
        fecha_inicio = request.args.get('fecha_inicio')
        fecha_fin = request.args.get('fecha_fin')

        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        
        # Consulta para obtener las ventas totales en el período dado
        query = """
            SELECT SUM(total_amount) AS ventas_totales 
            FROM pedidos 
            WHERE created_at BETWEEN %s AND %s;
        """
        cursor.execute(query, (fecha_inicio, fecha_fin))
        resultado = cursor.fetchone()

        return jsonify(resultado), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/totalProductosVendidos', methods=['GET'])
def total_productos_vendidos():
    try:
        # Obtener los parámetros de fecha de la solicitud
        fecha_inicio = request.args.get('fecha_inicio')
        fecha_fin = request.args.get('fecha_fin')

        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        
        # Consulta para obtener el total de productos vendidos en el período dado
        query = """
            SELECT SUM(dp.quantity) AS total_productos_vendidos
            FROM detalles_pedido dp
            JOIN pedidos p ON dp.order_id = p.order_id
            WHERE p.created_at BETWEEN %s AND %s;
        """
        cursor.execute(query, (fecha_inicio, fecha_fin))
        resultado = cursor.fetchone()

        return jsonify(resultado), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/productoMasVendidoPorFechas', methods=['GET'])
def producto_mas_vendido_por_fechas():
    try:
        # Obtener los parámetros de fecha de la solicitud
        fecha_inicio = request.args.get('fecha_inicio')
        fecha_fin = request.args.get('fecha_fin')

        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)
        
        # Consulta para obtener el producto más vendido en el período dado
        query = """
            SELECT pr.name AS producto, SUM(dp.quantity) AS total_vendido
            FROM detalles_pedido dp
            JOIN producto pr ON dp.product_id = pr.product_id
            JOIN pedidos p ON dp.order_id = p.order_id
            WHERE p.created_at BETWEEN %s AND %s
            GROUP BY pr.name
            ORDER BY total_vendido DESC
            LIMIT 1;
        """
        cursor.execute(query, (fecha_inicio, fecha_fin))
        resultado = cursor.fetchone()

        return jsonify(resultado), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()


@app.route('/api/totalVentasYGanancias', methods=['GET'])
def total_ventas_y_ganancias():
    try:
        # Obtener los parámetros de fecha de la solicitud
        fecha_inicio = request.args.get('fecha_inicio')
        fecha_fin = request.args.get('fecha_fin')

        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        # Consulta para obtener el total de ventas y ganancias en el período dado
        query = """
            SELECT SUM(p.total_amount) AS total_ventas, 
                   SUM((dp.sold_price - pr.cost_price) * dp.quantity) AS total_ganancias
            FROM pedidos p
            JOIN detalles_pedido dp ON p.order_id = dp.order_id
            JOIN producto pr ON dp.product_id = pr.product_id
            WHERE p.created_at BETWEEN %s AND %s
        """
        cursor.execute(query, (fecha_inicio, fecha_fin))
        resultado = cursor.fetchone()

        return jsonify(resultado), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/productoMasVendidoGeneral', methods=['GET'])
def producto_mas_vendido_general():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        query = """
            SELECT pr.name, SUM(dp.quantity) as total_vendido
            FROM detalles_pedido dp
            JOIN producto pr ON dp.product_id = pr.product_id
            GROUP BY pr.name
            ORDER BY total_vendido DESC
            LIMIT 1
        """
        cursor.execute(query)
        producto = cursor.fetchone()

        return jsonify(producto), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()


@app.route('/api/clienteMasPedidos', methods=['GET'])
def cliente_mas_pedidos():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        # Consulta para obtener el cliente que ha realizado más pedidos
        query = """
            SELECT u.first_name AS cliente, COUNT(p.order_id) AS total_pedidos
            FROM usuario u
            JOIN pedidos p ON u.user_id = p.user_id
            GROUP BY u.first_name
            ORDER BY total_pedidos DESC
            LIMIT 1
        """
        cursor.execute(query)
        resultado = cursor.fetchone()

        return jsonify(resultado), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/totalProductosPorCategoria', methods=['GET'])
def total_productos_por_categoria():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        # Consulta para obtener el total de productos vendidos por categoría
        query = """
            SELECT c.category_name AS categoria, SUM(dp.quantity) AS total_vendidos
            FROM detalles_pedido dp
            JOIN producto p ON dp.product_id = p.product_id
            JOIN categoria c ON p.category_id = c.category_id
            GROUP BY c.category_name
            ORDER BY total_vendidos DESC
        """
        cursor.execute(query)
        resultados = cursor.fetchall()

        return jsonify(resultados), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/usuariosRegistradosPorMes', methods=['GET'])
def usuarios_registrados_por_mes():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        # Consulta para obtener el número total de usuarios registrados por mes
        query = """
            SELECT DATE_FORMAT(created_at, '%Y-%m') AS mes, COUNT(user_id) AS total_registrados
            FROM usuario
            GROUP BY mes
            ORDER BY mes DESC
        """
        cursor.execute(query)
        resultados = cursor.fetchall()

        return jsonify(resultados), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

@app.route('/api/productoMasVendidoCantidad', methods=['GET'])
def producto_mas_vendido_cantidad():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        # Consulta para obtener el producto más vendido
        query = """
            SELECT pr.name AS producto, SUM(dp.quantity) AS cantidad_vendida
            FROM detalles_pedido dp
            JOIN producto pr ON dp.product_id = pr.product_id
            GROUP BY pr.name
            ORDER BY cantidad_vendida DESC
            LIMIT 1
        """
        cursor.execute(query)
        resultado = cursor.fetchone()

        return jsonify(resultado), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()


@app.route('/api/ingresosTotales', methods=['GET'])
def ingresos_totales():
    try:
        connection = get_db_connection()
        if connection is None:
            return jsonify({'error': 'No se pudo conectar a la base de datos.'}), 500

        cursor = connection.cursor(dictionary=True)

        query = """
            SELECT SUM(p.total_amount) as ingresos_totales
            FROM pedidos p
        """
        cursor.execute(query)
        ingresos = cursor.fetchone()

        return jsonify(ingresos), 200

    except Error as e:
        return jsonify({'error': str(e)}), 500

    finally:
        if connection and connection.is_connected():
            cursor.close()
            connection.close()

if __name__ == '__main__':
    #//context = ( 'cert.pem', 'key.pem')  # Ruta a tu certificado y clave privada
    #app.run(host='0.0.0.0', port=5000, ssl_context=context)
    #app.run()
    app.run(host='0.0.0.0', port=5000)

