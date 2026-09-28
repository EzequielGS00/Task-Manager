# Task Manager

API REST para gestionar tareas con estados y prioridades, desarrollada con Java y Spring Boot. Utiliza PostgreSQL para persistir los datos y springdoc-openapi para documentar los endpoints.

**Instalación desde cero:** está pendiente incorporar los scripts de creación de tablas y los datos iniciales de estados y prioridades. La ejecución descrita aquí requiere una base de datos previamente preparada con el esquema del proyecto.

## Funcionalidades

- Crear, consultar, actualizar y eliminar tareas.
- Consultar una tarea por su UUID.
- Cambiar individualmente el estado o la prioridad de una tarea.
- Validar los datos de entrada mediante DTOs y Jakarta Validation.
- Consultar la documentación de la API desde Swagger UI.

Los estados y las prioridades son catálogos utilizados por las tareas. Actualmente no tienen controladores REST propios. El proyecto tampoco incluye frontend ni autenticación.

## Tecnologías

Versiones declaradas en `pom.xml` y en la configuración del Maven Wrapper:

| Tecnología | Versión o uso |
| --- | --- |
| Java | 21 |
| Spring Boot | 4.1.1 |
| Maven | 3.9.16, mediante Maven Wrapper |
| Spring Web MVC | Endpoints REST |
| Spring Data JPA / Hibernate | Persistencia y relaciones |
| PostgreSQL | Base de datos; versión del servidor pendiente de documentar |
| Jakarta Validation | Validación de solicitudes |
| Lombok | Generación de código repetitivo |
| springdoc-openapi | 3.1.1, OpenAPI y Swagger UI |

## Requisitos

- JDK 21 configurado en la terminal o en IntelliJ IDEA.
- PostgreSQL accesible, con el esquema y los catálogos del proyecto preparados.
- Acceso a Internet para descargar Maven y las dependencias durante la primera ejecución.
- Git, si se descarga mediante `git clone`.

Maven Wrapper está incluido: no hace falta instalar Maven por separado.

## Descargar el proyecto

```bash
git clone https://github.com/EzequielGS00/Task-Manager.git
cd Task-Manager
```

También se puede descargar desde **Code → Download ZIP** en GitHub y descomprimirlo.

## Preparar PostgreSQL

La aplicación contiene esta configuración:

```properties
spring.jpa.hibernate.ddl-auto=none
```

Hibernate no crea ni modifica las tablas con este valor. La base de datos también debe existir antes de ejecutar la aplicación. Consulta la [documentación de inicialización de Spring Boot](https://docs.spring.io/spring-boot/how-to/data-initialization.html).

Las entidades utilizan las siguientes tablas:

| Tabla | Entidad | Función |
| --- | --- | --- |
| `estado` | `Status` | Catálogo de estados |
| `prioridad` | `Priority` | Catálogo de prioridades |
| `tareas` | `Task` | Tareas, relacionadas con ambos catálogos |

El usuario de conexión debe poder acceder a estas tablas. Las entidades no declaran un esquema SQL explícito.

**Pendiente:** versionar el esquema SQL real y los datos iniciales de `estado` y `prioridad`. Crear únicamente una base vacía no permite utilizar los endpoints de tareas. Tampoco se incluyen actualmente migraciones de Flyway o Liquibase.

Si ya tienes preparada la base de datos, consulta los identificadores disponibles antes de enviar solicitudes:

```sql
SELECT id_estado, tipo_estado FROM estado ORDER BY id_estado;
SELECT id_prioridad, tipo_prioridad FROM prioridad ORDER BY id_prioridad;
```

## Variables de entorno

`src/main/resources/application.properties` obtiene la conexión de estas cinco variables:

| Variable | Descripción | Ejemplo local |
| --- | --- | --- |
| `DB_HOST` | Host de PostgreSQL | `localhost` |
| `DB_PORT` | Puerto de PostgreSQL | `5432` |
| `DB_NAME` | Nombre de la base existente | `task_manager` |
| `DB_USER` | Usuario con acceso a la base | `task_manager_app` |
| `DB_PASSWORD` | Contraseña del usuario | Configurar localmente |

Los ejemplos de la tabla no crean la base ni el usuario: reemplázalos por los valores de tu instalación. Las credenciales se configuran fuera del repositorio.

### Desde IntelliJ IDEA

1. Abre el proyecto como proyecto Maven y selecciona JDK 21 como SDK.
2. En **Run → Edit Configurations**, abre la configuración de `TaskManagerApplication`.
3. En **Environment variables**, agrega las cinco variables anteriores.
4. Ejecuta la clase `com.derk.Task_Manager.TaskManagerApplication`.

Las variables de esa configuración pertenecen al proceso que inicia IntelliJ. Para ejecutar Maven desde una terminal, configúralas también en esa terminal.

### Desde Git Bash, Linux o macOS

En la carpeta del proyecto, adapta estos valores a tu instalación. La contraseña se solicita sin mostrarla ni escribirla como parte del comando:

```bash
export DB_HOST='localhost'
export DB_PORT='5432'
export DB_NAME='task_manager'
export DB_USER='task_manager_app'
read -r -s -p 'Contraseña de PostgreSQL: ' DB_PASSWORD
echo
export DB_PASSWORD

bash ./mvnw spring-boot:run
```

Si utilizas PowerShell, configura las mismas variables en esa sesión y ejecuta:

```powershell
.\mvnw.cmd spring-boot:run
```

## Consultar la API

Con la configuración del repositorio, sin sobrescribir el puerto ni la ruta base:

- API: [http://localhost:8080/api/tasks](http://localhost:8080/api/tasks)
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Las rutas de documentación corresponden a los valores predeterminados de [springdoc-openapi](https://springdoc.org/).

## Endpoints

| Método | Ruta | Operación | Respuesta de éxito |
| --- | --- | --- | --- |
| `GET` | `/api/tasks` | Listar tareas | `200` |
| `GET` | `/api/tasks/{uuid}` | Obtener una tarea | `200` |
| `POST` | `/api/tasks` | Crear una tarea | `201` |
| `PUT` | `/api/tasks/{uuid}` | Actualizar los campos editables de una tarea | `200` |
| `DELETE` | `/api/tasks/{uuid}` | Eliminar permanentemente una tarea | `204`, sin cuerpo |
| `PATCH` | `/api/tasks/{uuid}/status` | Cambiar su estado | `200` |
| `PATCH` | `/api/tasks/{uuid}/priority` | Cambiar su prioridad | `200` |

Sustituye `{uuid}` por el identificador real de una tarea. Envía los cuerpos de las solicitudes como JSON con `Content-Type: application/json`.

### Crear una tarea

Ejemplo de cuerpo para `POST /api/tasks`:

```json
{
  "tituloTarea": "Documentar el proyecto",
  "descripcionTarea": "Agregar instrucciones de instalación y uso",
  "idEstado": 1,
  "idPrioridad": 1,
  "fechaLimite": "2030-12-31"
}
```

Los valores `1` son ejemplos: deben corresponder a registros existentes en los catálogos. Ajusta la fecha límite a hoy o una fecha futura. El servidor genera el UUID y asigna la fecha de creación.

El título es obligatorio y admite hasta 100 caracteres. El DTO admite una descripción opcional de hasta 500 caracteres. Estado, prioridad y fecha límite son obligatorios.

### Actualizar una tarea

`PUT /api/tasks/{uuid}` utiliza los mismos nombres de campos del ejemplo anterior. Requiere título, estado, prioridad y fecha límite. El DTO de actualización, a diferencia del de creación, no restringe la fecha límite a hoy o al futuro.

Para `PATCH /api/tasks/{uuid}/status`, envía:

```json
{
  "idEstado": 1
}
```

Para `PATCH /api/tasks/{uuid}/priority`, envía:

```json
{
  "idPrioridad": 2
}
```

Usa identificadores existentes en tu base. El cambio de estado no asigna automáticamente una fecha de finalización en la implementación actual.

### Respuestas y errores

Las respuestas de tareas utilizan `TaskResponseDto`. Su identificador se llama `uuidTask`. El campo `nombreTarea` de ese DTO contiene actualmente **el nombre del estado**; el título de la tarea está en `tituloTarea`.

- `400`: datos que no cumplen la validación o una solicitud que Spring no puede interpretar.
- `404`: tarea, estado o prioridad inexistentes; el manejador del proyecto devuelve un mensaje de texto.
- `204`: eliminación completada, sin cuerpo de respuesta.

## Pruebas y empaquetado

Para ejecutar la prueba unitaria del cambio de prioridad, que no necesita PostgreSQL:

```bash
bash ./mvnw -Dtest=TaskServiceTest test
```

Para ejecutar todas las pruebas y generar el JAR:

```bash
bash ./mvnw verify
```

La prueba `TaskManagerApplicationTests` carga el contexto completo de Spring y utiliza la configuración de conexión del proyecto. Antes de ejecutar toda la suite, configura las cinco variables y prepara una base de pruebas. No hay un perfil de pruebas con base aislada incluido.

En PowerShell, sustituye `bash ./mvnw` por `.\mvnw.cmd`.

Después de un empaquetado correcto, el JAR se ejecuta con las mismas variables de entorno:

```bash
java -jar target/Task-Manager-0.0.1-SNAPSHOT.jar
```

## Organización del código

| Ruta dentro de `src/main/java/com/derk/Task_Manager` | Contenido |
| --- | --- |
| `Task/controller` | Endpoints de tareas |
| `Task/service` | Operaciones y transacciones |
| `Task/dto` | Datos de entrada y respuesta |
| `Task/mapper` | Conversión entre entidades y DTOs |
| `Task/entity` y `Task/repository` | Entidad y persistencia de tareas |
| `Task/exception` | Excepciones y manejo de recursos inexistentes |
| `Status` y `Priority` | Entidades y repositorios de los catálogos |
| `config` | Información de OpenAPI |

## Licencia

El repositorio todavía no incluye un archivo `LICENSE`. La elección de licencia está pendiente.
