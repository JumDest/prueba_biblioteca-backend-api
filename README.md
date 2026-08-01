# API REST Biblioteca - Backend

Sistema backend desarrollado con **Java 17**, **Spring Boot 3** y **PostgreSQL** para la gestión de biblioteca (Usuarios, Libros, Ejemplares y Préstamos). Totalmente contenedorizado con Docker y parametrizado mediante variables de entorno.

---

##  Variables de Entorno (`.env`)
El proyecto no posee URLs ni puertos quemados en el código. Toda la configuración de puertos y base de datos se gestiona dinámicamente mediante el archivo `.env`.

---

##  Instrucciones de Ejecución (Solo 4 Comandos)

```bash
# 1. Clonar el repositorio
git clone [https://github.com/TU_USUARIO/biblioteca-backend-api.git](https://github.com/TU_USUARIO/biblioteca-backend-api.git)

# 2. Entrar a la carpeta del proyecto
cd biblioteca-backend-api

# 3. Copiar la plantilla de variables de entorno
cp .env.example .env

# 4. Construir y levantar los servicios con Docker (API + Base de Datos)
docker-compose up -d --build
