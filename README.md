
# Compra de Toallas Femeninas 📱🛒

<img src="https://github.com/user-attachments/assets/cc83cfca-7b0c-4a38-9b1f-52a2274e0462" width="200" height="400">

## Descripción

Esta aplicación móvil para Android que facilita la compra de toallas femeninas de manera rápida y segura. La aplicación permite a los usuarios registrarse, iniciar sesión, explorar productos, agregar productos al carrito y realizar compras de manera eficiente. Además, ofrece secciones informativas sobre la fundación, historial de compras y opciones de empoderamiento económico.

## Tabla de Contenidos
- [Descripción](##Descripción)
- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Estructura del Proyecto](#estructura-del-proyecto)
  - [Model](#model)
  - [ViewModel](#viewmodel)
  - [View](#view)
- [API Endpoints](#api-endpoints)
- [Navegación](#navegación)
- [Contribución](#contribución)
- [Licencia](#licencia)

## Características

- **Registro de Usuarios**: Permite a los usuarios crear una cuenta proporcionando información personal.
- **Inicio de Sesión**: Autenticación segura para acceder a la cuenta.
- **Exploración de Productos**: Navega a través de una lista de productos disponibles.
- **Carrito de Compras**: Agrega, elimina y gestiona productos en el carrito.
- **Checkout**: Proceso de confirmación de compra con resumen del pedido.
- **Historial de Compras**: Visualiza el historial de compras realizadas.
- **Sección "Conócenos"**: Información detallada sobre la fundación, misión, visión y empoderamiento económico.
- **Aviso de Privacidad**: Política de privacidad y devolución para garantizar la seguridad de los usuarios.
- **Interfaz Intuitiva**: Diseño moderno y amigable utilizando Jetpack Compose.
## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal.
- **Android Studio**: Entorno de desarrollo integrado (IDE).
- **Jetpack Compose**: Toolkit moderno para construir interfaces de usuario nativas.
- **Retrofit**: Biblioteca para realizar solicitudes HTTP.
- **LiveData & ViewModel**: Arquitectura de componentes de Android para manejar datos y ciclo de vida.
- **Coil**: Biblioteca para cargar imágenes de manera eficiente.
- **Navigation Component**: Gestión de la navegación entre pantallas.

## Requisitos

- **Android Studio**: [Descargar aquí](https://developer.android.com/studio).
- **Dispositivo Android o Emulador**: Para ejecutar la aplicación.
- **Servidor Backend**: Asegúrate de tener el servidor backend corriendo en `http://10.0.2.2:5000/` o actualiza la URL en `RetrofitClient.kt` según tu configuración.

##Instalación
Sigue estos pasos para clonar y configurar el proyecto en tu máquina local:

###1. Clona el Repositorio:
   ```
   git clone https://github.com/tu-usuario/nombre-del-repositorio.git
   ```
###2. Navega al Directorio del Proyecto:
   ```
   cd nombre-del-repositorio
   ```
###3. Abre el Proyecto en Android Studio:
   - Abre Android Studio.
   - Selecciona "Open an existing project".
   - Navega al directorio clonado y abre el proyecto.
### 4. Configura el Servidor Backend:

- Asegúrate de que el servidor backend esté corriendo en [http://10.0.2.2:5000/](http://10.0.2.2:5000/).
- Si está en una dirección diferente, actualiza la constante `BASE_URL` en `RetrofitClient.kt`:
  
  ```kotlin
  private const val BASE_URL = "http://direccion-de-tu-servidor/"

### 5. Construye y Ejecuta la Aplicación:
   - En Android Studio, haz clic en el botón "Run" o usa el atajo `Shift + F10`.
     
     <img width="431" alt="image" src="https://github.com/user-attachments/assets/1fbdfe45-1720-4893-b3a5-d61798bc994f">

## Uso

### Registro de Usuario

1. Abre la aplicación.
2. Navega a la sección de registro.
3. Completa el formulario con tu información personal y crea una cuenta.

### Inicio de Sesión

1. Ingresa tus credenciales (correo electrónico y contraseña).
2. Accede a tu cuenta para comenzar a explorar productos.

### Exploración y Compra

1. Navega por la lista de toallas femeninas disponibles.
2. Agrega los productos deseados al carrito.
3. Procede al checkout para finalizar la compra.

### Historial de Compras

- Accede a tu perfil para ver el historial de tus compras anteriores.

### Sección "Conócenos"

- Explora la información sobre la fundación, su misión, visión y programas de empoderamiento económico.

## Estructura del Proyecto

El proyecto está organizado en diferentes paquetes para mantener una arquitectura limpia y modular:

### Model
Contiene las clases de datos y la interfaz de la API.

- **ApiService.kt**: Define las operaciones de la API.
- **RetrofitClient.kt**: Configura Retrofit para interactuar con el servidor.
- **Compra.kt**, **Product.kt**, **ProductHistorial.kt**, **Usuario.kt**, **LoginUsuario.kt**, **MatchResponse.kt**: Clases de datos que representan entidades de la aplicación.

### ViewModel
Maneja la lógica de negocio y la interacción con los modelos.

- **UsuarioViewModel.kt**: Maneja el estado y la lógica relacionada con los usuarios, compras, productos y autenticación.

### View
Contiene las interfaces de usuario creadas con Jetpack Compose.

#### Pantallas Principales
- **MainScreen.kt**: Pantalla de inicio con opciones para iniciar sesión, registrarse o acceder como invitado.
- **LoginScreen.kt**: Pantalla de inicio de sesión con validación de entradas y autenticación.
- **SignUpScreen.kt**: Pantalla de registro de nuevos usuarios con validación de campos.
- **HomeScreen.kt**: Pantalla de inicio después de iniciar sesión, con acceso al catálogo y otras secciones.
- **MenuPrincipal.kt**: Menú principal con opciones para navegar al catálogo, historial de compras, conocer la fundación y cerrar sesión.
- **CatalogoScreen.kt**: Pantalla que muestra el catálogo de productos disponibles.
- **Carrito.kt**: Pantalla del carrito de compras donde se gestionan los productos seleccionados.
- **Checkout.kt**: Pantalla de confirmación de compra con resumen del pedido.
- **HistorialCompras.kt**: Pantalla que muestra el historial de compras realizadas.
- **ConocenosScreen.kt**: Pantalla informativa sobre la fundación, misión, visión y empoderamiento económico.
- **AvisoPrivacidadScreen.kt**: Pantalla que muestra el aviso de privacidad y política de devolución.
- **DetalleProducto.kt** & **DetalleHistoriaScreen.kt**: Pantallas de detalle para productos y compras históricas.

#### Componentes Reutilizables
- **AppButton.kt**: Botón personalizado reutilizable en toda la aplicación.
- **InputField.kt**: Campo de entrada de texto personalizado con soporte para contraseñas.
- **TiledButton.kt**: Botón en forma de mosaico para el menú principal.
- **ProductoComposable.kt** & **ProductoHistorialComposable.kt**: Composables para representar productos en listas.
- **AppTitle.kt**: Título estilizado de la aplicación.

## API Endpoints
La aplicación se comunica con un servidor backend a través de diferentes endpoints. A continuación, se describen las principales rutas utilizadas:

- ### Obtener Usuarios
  - **Método:** `GET`
  - **URL:** `/api/obtenerUsuario`
  - **Descripción:** Obtiene la lista de usuarios registrados.
- ### Registrar Usuario:
  - Método: POST `URL: /api/registrarUsuario`
  - Descripción: Registra un nuevo usuario en la base de datos.
  - Cuerpo de la Solicitud:
    ```
      {
      "nombre": "String",
      "apellido": "String",
      "email": "String",
      "contraseña": "String",
      "telefono": "String",
      "edad": "String",
      "curp": "String",
      "creacion": "String"
      }
    ```  
-  ### Iniciar Sesión:
  -  Método: POST
  -  URL: `/api/iniciarSesion`
  -  Descripción: Autentica a un usuario y verifica sus credenciales.
  -  Cuerpo de la Solicitud:
      ```
      {
        "email": "String",
        "contraseña": "String"
      } 
  
      ```
- ### Obtener Productos:
  - Método: GET
  - URL: /api/obtenerProductos
  - Descripción: Obtiene la lista de productos disponibles.
 
- ###Registrar Compra:
-   Método: POST
-   URL: /api/registrarCompra
-   Descripción: Registra una nueva compra en la base de datos.
-   Cuerpo de la Solicitud
    ```
    {
      "curp": "String",
      "products": [
        {
          "product_id": "Int",
          "name": "String",
          "description": "String",
          "price": "Double",
          "stock_quantity": "Int",
          "category_id": "Int",
          "created_at": "String",
          "image_route": "String"
        }
      ]
    }
    ```
-  ###Obtener Productos Historial:
  -  Método: POST
  -  URL: /api/obtenerProductosHistorial
  -  Descripción: Obtiene el historial de productos comprados por un usuario específico.
  -  Cuerpo de la Solicitud:
     `
     {
      "email": "String"
      }
     `
- ## Navegación
  La navegación dentro de la aplicación está gestionada por el Navigation Component de Jetpack Compose. A continuación, se detallan las rutas principales y su propósito:

  - **"main"**: Pantalla de inicio con opciones para iniciar sesión, registrarse o acceder como invitado.
  - **"login"**: Pantalla de inicio de sesión.
  - **"signup"**: Pantalla de registro de nuevos usuarios.
  - **"menu_principal"**: Menú principal después de iniciar sesión.
  - **"catalogo"**: Pantalla que muestra el catálogo de productos.
  - **"Carrito"**: Pantalla del carrito de compras.
  - **"Checkout"**: Pantalla de confirmación de compra.
  - **"historial"**: Pantalla que muestra el historial de compras.
  - **"conocenos"**: Pantalla informativa sobre la fundación.
  - **"aviso_privacidad"**: Pantalla que muestra el aviso de privacidad.
  - **"detalleProducto/{productId}"**: Pantalla de detalle para un producto específico.
  - **"detalleHistorial/{productId}"**: Pantalla de detalle para una compra histórica específica.
- ## Ejemplo de Navegación
  ```
  NavHost(navController = navController, startDestination = "main") {
    composable("signup") { SignUpScreen(navController, viewModel) }
    composable("main") { MainScreen(navController, viewModel) }
    composable("catalogo") { CatalogoScreen(navController, viewModel) }
    composable("login") { LoginScreen(navController, viewModel) }
    composable("menu_principal") { MenuPrincipal(navController, viewModel) }
    composable("Carrito") { Carrito(navController, viewModel) }
    composable("Checkout") { Checkout(navController, viewModel) }
    // Más rutas...
  }

  ```
## Contribución
¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, sigue estos pasos:
1. Clona el repositorio.
2. Crea una rama para tu característica (git checkout -b feature/nueva-caracteristica).
3. Commit tus cambios (git commit -m 'Añadir nueva característica').
4. Push a la rama (git push origin feature/nueva-caracteristica).
5. Abre un Pull Request.
Asegúrate de seguir las Buenas Prácticas y de que tus contribuciones mantengan la coherencia con la arquitectura del proyecto.

## Autores:
Arturo Alejandro Paniagua Romero - A01749736
Luis Ubaldo Balderas Sánchez - A01751150
Iván Alexander Ramos Ramírez - A01750817
Andrea Elizabeth Román Varela - A01749760


  
  
