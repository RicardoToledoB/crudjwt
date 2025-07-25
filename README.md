
# 🛡️ CRUD con Autenticación JWT - Spring Boot

Este proyecto es una API REST creada con Spring Boot que implementa autenticación basada en JWT (JSON Web Token) y operaciones CRUD sobre una entidad `TaskEntity`. Los usuarios pueden registrarse, iniciar sesión y operar sobre tareas una vez autenticados.

---

## 📌 Características principales

- ✅ Registro y autenticación de usuarios
- 🔐 Seguridad con JWT (token de acceso y validaciones)
- 👤 Control de acceso básico
- 📄 CRUD completo sobre entidad `Task`
- 📦 Arquitectura basada en DTOs
- 💾 Base de datos integrada (H2 en memoria)

---

## 🧱 Estructura del proyecto

```
src/
├── controller/                 # Controladores REST (Auth y Tasks)
├── dto/                        # Objetos de transferencia (DTOs)
├── entity/                     # Entidades JPA (User, Task)
├── repository/                 # Interfaces de acceso a datos
├── security/                   # Configuración de seguridad y filtros JWT
├── service/                    # Implementación de lógica de negocio
└── SecurityApplication.java    # Clase principal de Spring Boot
```

---

## 🔐 Seguridad

**JWT** se utiliza para autenticar usuarios. Una vez logueado, el usuario recibe un token que debe enviar en cada solicitud como:

```
Authorization: Bearer <token>
```

### Componentes clave

- `SecurityConfig.java`: Define los endpoints públicos y protegidos.
- `JwtTokenProvider.java`: Genera y valida tokens JWT.
- `JwtAuthenticationFilter.java`: Filtra y verifica las solicitudes con tokens.
- `UserDetailsServiceImpl.java`: Carga datos del usuario desde la base de datos.

---

## 📋 Endpoints principales

### 🔑 Autenticación

| Método | URL             | Descripción                  |
|--------|------------------|------------------------------|
| POST   | `/auth/register`| Registrar nuevo usuario      |
| POST   | `/auth/login`   | Iniciar sesión y obtener JWT |

### 📦 Gestión de tareas

| Método | URL                    | Descripción                     |
|--------|-------------------------|---------------------------------|
| GET    | `/api/v1/tasks`         | Listar todas las tareas         |
| POST   | `/api/v1/tasks`         | Crear una nueva tarea           |
| PUT    | `/api/v1/tasks/{id}`    | Actualizar una tarea existente  |
| DELETE | `/api/v1/tasks/{id}`    | Eliminar una tarea por su ID    |

> ⚠️ Todos estos requieren token JWT.

---

## 📦 Entidades y DTOs

### `UserEntity.java`

```java
@Id
@GeneratedValue
private Long id;
private String username;
private String password;
```

### `TaskEntity.java`

```java
@Id
@GeneratedValue
private Long id;
private String title;
private String description;
@ManyToOne
private UserEntity user;
```

### `TaskDTO.java`

```java
private Long id;
private String title;
private String description;
private Long userId;
```

---

## ▶️ Cómo ejecutar

1. Clonar el repositorio:

```bash
git clone https://github.com/RicardoToledoB/crudjwt.git
cd crudjwt
```

2. Ejecutar el proyecto:

```bash
./mvnw spring-boot:run
```

3. Acceder a Swagger UI (si habilitado):

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚙️ application.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
jwt.secret=secret123
jwt.expiration=3600000
```

---

## 🧪 Pruebas

Puedes usar Postman o Swagger para probar los siguientes flujos:

1. Registro → `/auth/register`
2. Login → `/auth/login` (copiar JWT)
3. Acceder a `/api/v1/tasks` usando el JWT en el header

---

## 📄 Licencia

MIT License / Licencia personalizada – por definir.

---

## 📞 Contacto

- Autor: Ricardo Toledo  
- GitHub: [@RicardoToledoB](https://github.com/RicardoToledoB)
