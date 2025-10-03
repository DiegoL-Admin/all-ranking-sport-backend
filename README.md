# 🏆 All Ranking Sport - Backend

Este es el backend de la plataforma de comercio electrónico **All Ranking Sport**, desarrollado con **Spring Boot**. Proporciona una API RESTful para la gestión de productos (CRUD), autenticación de usuarios basada en JWT y un chatbot de inteligencia artificial impulsado por **Google Gemini** para responder preguntas relacionadas con los productos.

---

## 🚀 Características principales

- 🛍️ **Gestión de productos (CRUD):** Crear, leer, actualizar y eliminar productos.
- 🔐 **Autenticación de usuarios:** Registro e inicio de sesión seguros utilizando **JSON Web Tokens (JWT)**.
- 🤖 **Chatbot con IA:** Asistente inteligente (basado en Google Gemini) que responde preguntas sobre los productos.
- 🗄️ **Base de datos MySQL:** Almacenamiento persistente para productos y usuarios.
- 🛡️ **Seguridad:** Control de acceso basado en roles (`isAuthenticated()`) para endpoints protegidos.

---

## 🧰 Tecnologías utilizadas

- ☕ **Lenguaje:** Java 17
- 🖥️ **Framework:** Spring Boot 3.2.5
- 🛠️ **Herramienta de build:** Gradle
- 🗃️ **Base de datos:** MySQL
- 🪶 **ORM:** Spring Data JPA / Hibernate
- 🔑 **Seguridad:** Spring Security + JWT (jjwt)
- 🤖 **IA:** Google Gemini API (`google-cloud-vertexai`)
- ✨ **Utilidades:** Lombok

---

## ⚙️ Requisitos previos

Antes de iniciar el proyecto, asegúrate de tener instalado:

- ☕ **JDK 17** o superior
- 🗄️ **MySQL Server** (versión 8.0 o superior recomendada)
- 🧱 **Gradle** (generalmente incluido en proyectos Spring Boot)
- 🔑 **Proyecto en Google Cloud con Vertex AI habilitado**
- 🤖 **Clave de API de Gemini** (desde Google AI Studio o Google Cloud)

---

## 🗃️ Configuración de la base de datos

1. Crea la base de datos en MySQL:
```sql
CREATE DATABASE allrankingdb;
```
## 🔧 Configuración del proyecto
Edita src/main/resources/application.properties:

## 🗄️ Conexión a MySQL:
```properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/allrankingdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
```
## 🤖 Configuración de Gemini:
```properties
Copiar código
gemini.api.key=TU_GEMINI_API_KEY
gemini.project.id=tu-gcp-project-id
gemini.location=us-central1
```
## ⚠️ Reemplaza los valores con los datos reales de tu proyecto.

🏃‍♂️ Ejecución del proyecto
Navega al directorio del proyecto:
```
bash
Copiar código
cd D:\Proyectos\gemini-cli\all-ranking-sport-backend-new
Compila el proyecto:
```
bash
Copiar código
./gradlew clean build
Ejecuta la aplicación:

bash
Copiar código
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
✅ La aplicación se iniciará en http://localhost:8080

## 🔗 Endpoints principales
## 🔑 Autenticación
POST /api/auth/register → Registrar un nuevo usuario

POST /api/auth/login → Autenticar usuario y recibir token JWT

## 📦 Productos
GET /api/products → Listar todos los productos

GET /api/products/{id} → Obtener producto por ID

POST /api/products → Crear producto (requiere autenticación)

PUT /api/products/{id} → Actualizar producto (requiere autenticación)

DELETE /api/products/{id} → Eliminar producto (requiere autenticación)

## 🤖 Chatbot con IA
POST /api/chat → Enviar mensaje al asistente y recibir respuesta (requiere autenticación)

## 🔐 Seguridad
Este backend utiliza JWT (JSON Web Tokens) para proteger los endpoints.
Después de iniciar sesión, el token debe enviarse en cada petición protegida en el encabezado:

makefile
Copiar código
Authorization: Bearer <token>
## 🖥️ Integración con el Frontend
Este backend está diseñado para funcionar junto con un frontend desarrollado en Angular, el cual consumirá estas APIs REST para autenticación, gestión de productos y consultas al chatbot.

## 📜 Licencia
Este proyecto es de uso interno para la plataforma All Ranking Sport.
Puedes modificarlo o adaptarlo según las necesidades de tu proyecto.
