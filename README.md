# 🏋️ GymRoutine

GymRoutine es una aplicación web desarrollada con Java y Spring Boot para gestionar y consultar ejercicios de gimnasio organizados por grupos musculares.

El proyecto permite crear una biblioteca de ejercicios con información sobre su ejecución, material necesario, errores frecuentes, imágenes y vídeos.

## 🚀 Funcionalidades

### Ejercicios

- Listado de ejercicios.
- Crear nuevos ejercicios.
- Consultar el detalle de un ejercicio.
- Editar ejercicios.
- Eliminar ejercicios.
- Buscar ejercicios por nombre.
- Filtrar ejercicios por grupo muscular.
- Combinar búsqueda por nombre y grupo muscular.
- Control de nombres duplicados.
- Validación de formularios.
- Gestión de ejercicios inexistentes.

### Grupos musculares

- Listado de grupos musculares.
- Crear grupos musculares.
- Consultar el detalle de un grupo.
- Editar grupos musculares.
- Eliminar grupos musculares.
- Control de nombres duplicados.
- Protección frente a la eliminación de grupos que tienen ejercicios asociados.
- Gestión de grupos musculares inexistentes.

### Gestión de errores

La aplicación utiliza excepciones personalizadas para controlar situaciones como:

- Ejercicio duplicado.
- Ejercicio no encontrado.
- Grupo muscular duplicado.
- Grupo muscular no encontrado.
- Grupo muscular con ejercicios asociados.

Los errores se muestran mediante mensajes de validación o páginas de error personalizadas.

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- Jakarta Validation
- MariaDB
- Maven
- JUnit 5
- Mockito
- HTML
- CSS
- Git
- GitHub

## 🏗️ Arquitectura

El proyecto sigue una arquitectura por capas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Base de datos
```

### Entity

Representa las entidades almacenadas en la base de datos.

Entidades principales:

- `Ejercicio`
- `GrupoMuscular`

### Repository

Gestiona el acceso a la base de datos mediante Spring Data JPA.

### Service

Contiene la lógica de negocio de la aplicación.

Por ejemplo:

- Control de duplicados.
- Búsquedas.
- Validación de operaciones.
- Protección de grupos musculares en uso.
- Gestión de recursos inexistentes.

### Controller

Recibe las peticiones HTTP, utiliza los servicios y envía los datos necesarios a las vistas Thymeleaf.

### Templates

Contienen las páginas HTML renderizadas mediante Thymeleaf.

## 🔎 Búsqueda de ejercicios

GymRoutine permite realizar diferentes tipos de búsqueda:

```text
Sin filtros
→ muestra todos los ejercicios

Nombre
→ busca ejercicios cuyo nombre contenga el texto introducido

Grupo muscular
→ muestra los ejercicios pertenecientes al grupo seleccionado

Nombre + grupo muscular
→ combina ambos filtros
```

Las consultas se realizan mediante métodos derivados de Spring Data JPA.

## 🧪 Tests

El proyecto dispone de tests automáticos para comprobar la lógica de los servicios.

Se utilizan:

- JUnit 5
- Mockito

Actualmente se prueban casos como:

- Creación correcta.
- Detección de duplicados.
- Edición.
- Obtención por ID.
- Recursos inexistentes.
- Búsquedas.
- Eliminación.
- Protección de grupos musculares con ejercicios asociados.

Para ejecutar los tests:

```bash
mvn test
```

Estado de la V1:

```text
Tests run: 20
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

## ▶️ Ejecutar el proyecto

### Requisitos

Es necesario tener instalado:

- Java 21 o superior compatible
- Maven
- MariaDB

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
```

### 2. Entrar en el proyecto

```bash
cd GymRoutine
```

### 3. Configurar la base de datos

Configura la conexión a MariaDB en:

```text
src/main/resources/application.properties
```

### 4. Ejecutar los tests

```bash
mvn test
```

### 5. Iniciar la aplicación

```bash
mvn spring-boot:run
```

Por defecto, la aplicación estará disponible en:

```text
http://localhost:8080
```

## 📂 Estructura principal

```text
src/
├── main/
│   ├── java/
│   │   └── dev/adriangabas/gymroutine/
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── exception/
│   │       ├── repository/
│   │       └── service/
│   │
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
│
└── test/
    └── java/
        └── dev/adriangabas/gymroutine/
            └── service/
```

## 📌 Estado del proyecto

### V1

La primera versión incluye el núcleo de gestión de ejercicios y grupos musculares:

- CRUD.
- Búsquedas y filtros.
- Validaciones.
- Gestión de duplicados.
- Manejo de excepciones.
- Páginas de error.
- Tests unitarios.

## 🔮 Posibles mejoras futuras

Algunas funcionalidades que podrían incorporarse en versiones posteriores:

- Creación de rutinas de entrenamiento.
- Asociación de ejercicios a rutinas.
- Usuarios.
- Autenticación y autorización con Spring Security.
- Favoritos.
- API REST.
- Aplicación Android.
- Mejoras visuales y responsive.

## 👨‍💻 Autor

Adrián Gabas

Proyecto desarrollado como práctica de aprendizaje de Java, Spring Boot, JPA, Thymeleaf y desarrollo backend.
