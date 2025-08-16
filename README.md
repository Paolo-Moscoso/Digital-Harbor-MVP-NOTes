# MVP NOTES APP - Monorepo

Este repositorio contiene varios microservicios para una aplicación de notas, implementados con Spring Boot y arquitectura de microservicios.

## Proyectos incluidos

- `auth-service`: Servicio de autenticación y autorización.
- `discovery-service`: Servicio de descubrimiento (Eureka).
- `gateway-api-service`: API Gateway para enrutar peticiones.
- `note-sevice`: Servicio principal para gestión de notas.
- `user-service`: Servicio para gestión de usuarios.

## Arquitectura


Cada microservicio implementa una arquitectura basada en capas (Controller, Service, Repository) y expone endpoints RESTful. El API Gateway y Eureka permiten la comunicación y descubrimiento entre servicios.

### Arquitectura por microservicio

#### auth-service
- Arquitectura: Microservicio con capas Controller, Service, Repository. Utiliza JWT para autenticación.
- Endpoints principales:
	- `POST /api/auth/register` — Registro de usuario
		- Ejemplo: `curl -X POST http://localhost:8081/api/auth/register -H "Content-Type: application/json" -d '{"username":"user","password":"pass"}'`
	- `POST /api/auth/login` — Login de usuario
		- Ejemplo: `curl -X POST http://localhost:8081/api/auth/login -H "Content-Type: application/json" -d '{"username":"user","password":"pass"}'`
	- `GET /api/auth` — Información de autenticación

#### discovery-service
- Arquitectura: Eureka Server para descubrimiento de microservicios. No expone endpoints REST propios.

#### gateway-api-service
- Arquitectura: API Gateway basado en Spring Cloud Gateway. Enruta peticiones a los microservicios. No expone endpoints propios, solo enruta.

#### note-sevice
- Arquitectura: Microservicio con capas Controller, Service, Repository. Gestiona notas y etiquetas.
- Endpoints principales:
	- `POST /api/notes` — Crear nota
		- Ejemplo: `curl -X POST http://localhost:8083/api/notes -H "Content-Type: application/json" -d '{"title":"Nota","content":"Contenido"}'`
	- `PUT /api/notes/{id}` — Actualizar nota
		- Ejemplo: `curl -X PUT http://localhost:8083/api/notes/1 -H "Content-Type: application/json" -d '{"title":"Nuevo título"}'`
	- `GET /api/notes/{id}` — Obtener nota por ID
		- Ejemplo: `curl http://localhost:8083/api/notes/1`
	- `GET /api/notes` — Listar notas
		- Ejemplo: `curl http://localhost:8083/api/notes`
	- `DELETE /api/notes/{id}` — Eliminar nota
		- Ejemplo: `curl -X DELETE http://localhost:8083/api/notes/1`
	- `GET /api/notes/search/title?title=algo` — Buscar por título
		- Ejemplo: `curl http://localhost:8083/api/notes/search/title?title=algo`
	- `GET /api/notes/search/content?content=algo` — Buscar por contenido
		- Ejemplo: `curl http://localhost:8083/api/notes/search/content?content=algo`
	- `GET /api/notes/search/tags?tags=tag1,tag2` — Buscar por etiquetas
		- Ejemplo: `curl http://localhost:8083/api/notes/search/tags?tags=tag1,tag2`
	- `GET /api/notes/archived` — Listar notas archivadas

	- `POST /api/tags` — Crear etiqueta
		- Ejemplo: `curl -X POST http://localhost:8083/api/tags -H "Content-Type: application/json" -d '{"name":"importante"}'`
	- `GET /api/tags` — Listar etiquetas
		- Ejemplo: `curl http://localhost:8083/api/tags`
	- `GET /api/tags/search?name=algo` — Buscar etiqueta por nombre
		- Ejemplo: `curl http://localhost:8083/api/tags/search?name=algo`
	- `DELETE /api/tags/{tagId}` — Eliminar etiqueta
		- Ejemplo: `curl -X DELETE http://localhost:8083/api/tags/1`

#### user-service
- Arquitectura: Microservicio con capas Controller, Service, Repository. Gestiona usuarios y roles.
- Endpoints principales:
	- `POST /api/users` — Crear usuario
		- Ejemplo: `curl -X POST http://localhost:8084/api/users -H "Content-Type: application/json" -d '{"username":"nuevo","password":"pass"}'`
	- `GET /api/users/{id}` — Obtener usuario por ID
		- Ejemplo: `curl http://localhost:8084/api/users/1`
	- `GET /api/users` — Listar usuarios
		- Ejemplo: `curl http://localhost:8084/api/users`
	- `PUT /api/users/{id}` — Actualizar usuario
		- Ejemplo: `curl -X PUT http://localhost:8084/api/users/1 -H "Content-Type: application/json" -d '{"username":"editado"}'`
	- `DELETE /api/users/{id}` — Eliminar usuario
		- Ejemplo: `curl -X DELETE http://localhost:8084/api/users/1`

	- `POST /api/roles` — Crear rol
		- Ejemplo: `curl -X POST http://localhost:8084/api/roles -H "Content-Type: application/json" -d '{"name":"ADMIN"}'`
	- `GET /api/roles/{id}` — Obtener rol por ID
		- Ejemplo: `curl http://localhost:8084/api/roles/1`
	- `GET /api/roles/name/{name}` — Obtener rol por nombre
		- Ejemplo: `curl http://localhost:8084/api/roles/name/ADMIN`
	- `GET /api/roles` — Listar roles
		- Ejemplo: `curl http://localhost:8084/api/roles`

## Instalación y requisitos

1. **Java 17+**: Todos los servicios requieren Java 17 o superior.
2. **Maven**: Para compilar y gestionar dependencias.
3. **Base de datos**: Se utiliza PostgreSQL (puedes modificar la configuración en los archivos `application.properties` de cada servicio).
4. **Docker (opcional)**: Para levantar los servicios y la base de datos de forma sencilla.

### Pasos de instalación

```sh
# Clona el repositorio
git clone <url-del-repo>
cd MVP_NOTES_APP

# Compila cada servicio
cd auth-service/auth-service
mvn clean install

cd ../../discovery-service/discovery-service
mvn clean install

cd ../../gateway-api-service/gateway-api-service
mvn clean install

cd ../../note-sevice/note-sevice
mvn clean install

cd ../../user-service/user-service
mvn clean install
```

> Puedes levantar los servicios ejecutando los JARs generados en la carpeta `target` de cada microservicio.

## Dependencias principales

- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Cloud Eureka Server/Client
- Spring Cloud Gateway
- Spring Data JPA
- PostgreSQL Driver
- JWT (jjwt)

Las dependencias específicas están definidas en los archivos `pom.xml` de cada servicio.

## Base de datos

- **PostgreSQL**: Todos los servicios que requieren persistencia utilizan PostgreSQL. Configura la conexión en los archivos `application.properties`.
