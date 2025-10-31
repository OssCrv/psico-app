# psico-app

## Descripción
psico-app es un backend construido con Spring Boot que explora la creación de un sistema de reservas para psicólogos, con gestión de edificios, consultorios, terapeutas y turnos.【F:pom.xml†L16-L18】

## Arquitectura y tecnologías
- **Spring Boot 2.6** para el arranque y la configuración de la aplicación.【F:pom.xml†L7-L47】
- **Spring Web** para la exposición de API RESTful.【F:pom.xml†L22-L45】
- **Spring Data JPA** junto con **MySQL** como base de datos relacional.【F:pom.xml†L24-L35】
- **Spring Security** con autenticación basada en JWT para proteger los recursos.【F:pom.xml†L32-L41】【F:src/main/java/com/psicotaller/psicoapp/backend/web/security/SecurityConfig.java†L1-L70】
- **MapStruct** para el mapeo entre entidades y DTOs y **Lombok** para reducir código repetitivo.【F:pom.xml†L37-L77】

## Componentes clave
- **Controladores REST** que gestionan CRUD de edificios, consultorios y reservas bajo la ruta base `/api/v1`.【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/BuildingController.java†L10-L40】【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/FacilityController.java†L10-L39】【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/ReservationController.java†L10-L38】
- **Servicio de autenticación** que emite tokens JWT a partir de credenciales válidas en `/api/auth/authenticate`.【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/AuthController.java†L16-L57】
- **Servicios de dominio** que encapsulan la lógica de negocio y usan MapStruct para mapear DTOs y entidades.【F:src/main/java/com/psicotaller/psicoapp/backend/domain/impl/BuildingServiceImpl.java†L1-L73】
- **Entidades JPA** que representan usuarios, roles, edificios, consultorios y reservas.【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Building.java†L1-L34】【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Facility.java†L1-L37】【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Reservation.java†L1-L40】【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/UserApp.java†L1-L43】【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Role.java†L1-L19】
- **Seguridad sin estado** con filtro JWT y autenticación basada en usuarios persistidos en la base de datos.【F:src/main/java/com/psicotaller/psicoapp/backend/web/security/SecurityConfig.java†L1-L82】【F:src/main/java/com/psicotaller/psicoapp/backend/web/security/jwt/JwtFilterRequest.java†L1-L62】【F:src/main/java/com/psicotaller/psicoapp/backend/domain/impl/UserAppServiceImpl.java†L1-L56】

## Requisitos previos
- Java 11 o superior.
- Maven 3.8 o superior.
- Base de datos MySQL accesible para la aplicación.

## Configuración
1. Copia `src/main/resources/example-application.properties` a `src/main/resources/application-dev.properties` (o al perfil que quieras utilizar).【F:src/main/resources/example-application.properties†L1-L15】
2. Actualiza las credenciales y URL de la base de datos.
3. Define los parámetros del JWT (`application.jwt.*`) según tus necesidades.
4. Ajusta el puerto del servidor si lo deseas (por defecto 8090 en el ejemplo).【F:src/main/resources/example-application.properties†L1-L15】

La aplicación está configurada para usar el perfil `dev` y exponer sus endpoints bajo el contexto `/api`.【F:src/main/resources/application.properties†L1-L2】

## Ejecución
```bash
mvn spring-boot:run
```
Esto iniciará el servicio en el puerto y contexto definidos en las propiedades de configuración.

## Autenticación
1. Crea registros de usuarios en la tabla `users` con contraseñas codificadas según el `PasswordEncoder` configurado (BCrypt por defecto).【F:src/main/java/com/psicotaller/psicoapp/backend/web/security/PasswordConfig.java†L1-L13】
2. Realiza una petición `POST` a `/api/auth/authenticate` con un cuerpo JSON que incluya `username` y `password`.【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/AuthController.java†L16-L57】
3. Utiliza el token JWT devuelto en la cabecera `Authorization: Bearer <token>` para acceder a los endpoints protegidos.

## Endpoints principales
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/v1/buildings` | Listado de edificios registrados. |
| GET | `/api/v1/buildings/{buildingId}` | Recupera un edificio por su identificador. |
| POST | `/api/v1/buildings` | Crea un nuevo edificio. |
| PUT | `/api/v1/buildings` | Actualiza parcialmente un edificio existente. |
| DELETE | `/api/v1/buildings/{buildingId}` | Elimina un edificio. |
| GET | `/api/v1/facilities` | Listado de consultorios disponibles. |
| GET | `/api/v1/facilities/{facilityId}` | Recupera un consultorio específico. |
| POST | `/api/v1/facilities` | Crea un nuevo consultorio. |
| PUT | `/api/v1/facilities` | Actualiza parcialmente un consultorio existente. |
| DELETE | `/api/v1/facilities/{facilityId}` | Elimina un consultorio. |
| GET | `/api/v1/reservations` | Listado de reservas registradas. |
| GET | `/api/v1/reservations/{reservationId}` | Recupera una reserva puntual. |
| POST | `/api/v1/reservations` | Crea una nueva reserva. |
| PUT | `/api/v1/reservations` | Actualiza parcialmente una reserva existente. |
| DELETE | `/api/v1/reservations/{reservationId}` | Elimina una reserva. |
| POST | `/api/auth/authenticate` | Obtiene un token JWT válido. |

Consulta los controladores para conocer los detalles de implementación y respuesta de cada operación.【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/BuildingController.java†L10-L40】【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/FacilityController.java†L10-L39】【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/ReservationController.java†L10-L38】【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/AuthController.java†L16-L57】

### Cuerpos de solicitud de ejemplo
Los cuerpos JSON siguientes reflejan los atributos de la base de datos y DTOs involucrados en cada recurso.

#### Buildings
- **POST / PUT `/api/v1/buildings`**
  ```json
  {
    "id": 1,
    "ownersName": "Ricardo Escobar",
    "buildingsAddress": "Cl. 49b #64b - 96 int 202, Medellín, Antioquia",
    "buildingsName": "El Psicotaller"
  }
  ```
  - Para crear un registro nuevo vía `POST`, omite el campo `id` (se genera automáticamente). Los demás campos se corresponden con las columnas `owners_name`, `buildings_address` y `buildings_name` en la tabla `buildings`.【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Building.java†L18-L32】

#### Facilities
- **POST / PUT `/api/v1/facilities`**
  ```json
  {
    "id": 1,
    "fkBuilding": 1,
    "roomNumber": "Consultorio 1"
  }
  ```
  - `fkBuilding` debe apuntar a un `id` existente en `buildings`. Igual que con los edificios, el `id` es autogenerado y solo se envía en actualizaciones. El DTO también admite un objeto anidado `buildingDto` si se requiere retornar información adicional del edificio asociado.【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Facility.java†L19-L36】【F:src/main/java/com/psicotaller/psicoapp/backend/domain/dto/FacilityDto.java†L1-L15】

#### Reservations
- **POST / PUT `/api/v1/reservations`**
  ```json
  {
    "id": 1,
    "fkFacility": 1,
    "fkTherapist": 2,
    "reservationDate": "25/04/2022"
  }
  ```
  - `fkFacility` y `fkTherapist` deben referenciar registros válidos de `facilities` y `users` respectivamente. El campo `reservationDate` se envía como fecha siguiendo el patrón `dd/MM/yyyy` definido en la entidad, mientras que los valores de creación/actualización se gestionan automáticamente por la base de datos. También puede incluirse un `facilityDto` y `therapistDto` cuando se requiera devolver información expandida en las respuestas.【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Reservation.java†L21-L50】【F:src/main/java/com/psicotaller/psicoapp/backend/domain/dto/ReservationDto.java†L1-L17】【F:src/main/java/com/psicotaller/psicoapp/backend/domain/dto/TherapistDto.java†L1-L17】

#### Autenticación
- **POST `/api/auth/authenticate`**
  ```json
  {
    "username": "Admin",
    "password": "<tu-contraseña>"
  }
  ```
  - Devuelve un JWT que debe incluirse en la cabecera `Authorization: Bearer <token>` para invocar los endpoints protegidos. El cuerpo solicitado corresponde al DTO `AuthenticationRequest` utilizado por el controlador de autenticación.【F:src/main/java/com/psicotaller/psicoapp/backend/web/controller/AuthController.java†L28-L47】【F:src/main/java/com/psicotaller/psicoapp/backend/domain/dto/AuthenticationRequest.java†L1-L10】

## Estructura del proyecto
```
src/main/java/com/psicotaller/psicoapp
├── backend
│   ├── domain        # Servicios, DTOs, mapeadores y excepciones de negocio.
│   ├── persistence   # Entidades JPA y repositorios.
│   └── web           # Controladores REST y configuración de seguridad.
└── PsicoAppApplication.java # Clase principal de arranque.
```

## Desarrollo y pruebas
- Ejecuta `mvn clean install` para compilar el proyecto y verificar que todo compila.
- Considera activar las dependencias de prueba comentadas en el `pom.xml` si deseas añadir tests automatizados.【F:pom.xml†L45-L63】

## Próximos pasos sugeridos
- Agregar pruebas unitarias e integración para los servicios y controladores.
- Documentar los endpoints con OpenAPI/Swagger.
- Extender la gestión de roles y permisos para diferenciar pacientes, psicólogos y administradores.【F:src/main/java/com/psicotaller/psicoapp/backend/persistence/Role.java†L1-L15】
