import unittest
from servidor import app, hash_password_sha256, enviar_correo_confirmacion
import hashlib

class TestServidor(unittest.TestCase):

    def setUp(self):
        app.config['TESTING'] = True
        self.client = app.test_client()

    # Prueba unitaria de la función hash_password_sha256
    def test_hash_password_sha256(self):
        print("Ejecutando test_hash_password_sha256...")  # Debugging
        password = "password123"
        hashed = hash_password_sha256(password)
        self.assertEqual(hashed, hashlib.sha256(password.encode('utf-8')).hexdigest())
        print("Test hash_password_sha256: OK")

    # Prueba de enviar correo (simulada)
    def test_enviar_correo_confirmacion(self):
        print("Ejecutando test_enviar_correo_confirmacion...")  # Debugging
        outlook_user = "test@example.com"
        outlook_password = "password"
        destinatario = "client@example.com"
        subject = "Compra Confirmada"
        nombre_cliente = "Cliente Test"
        productos = [("Producto A", 2, 100.00)]
        total = 200.00
        
        enviar_correo_confirmacion(outlook_user, outlook_password, destinatario, subject, nombre_cliente, productos, total)
        print("Test enviar_correo_confirmacion: OK")
    
    # Prueba de la ruta /api/obtenerUsuario
    def test_get_data(self):
        print("Ejecutando test_get_data...")  # Debugging
        response = self.client.get('/api/obtenerUsuario')
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json, list)
        print("Test get_data: OK")

# Ejecuta las pruebas
if __name__ == '__main__':
    unittest.main()
