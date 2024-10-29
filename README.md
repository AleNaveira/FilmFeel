# FilmFeel API (under construction)

>  Note: This REST API is currently in a development phase, so some functionalities may be in the process of adjustment or expansion, and occasional errors may occur. Continuous improvements are being made to enhance stability and add new features. This REST API is currently in a development phase, so some functionalities may be in the process of adjustment or expansion, and occasional errors may occur. Continuous improvements are being made to enhance stability and add new features.
---------------------------------------------------------------

*This README is avaliable in [Español](#version-español) 


**Table of Contents**

- [1. Description](#1-description)
- [2. Key Features](#2-key-features)
  - [Movie Management](#movie-management)
  - [User and Role System](#user-and-role-system)
  - [User Interactions](#user-interactions)
  - [People-Film Association](#people-film-association)
  - [Interactive Documentation with Swagger](#interactive-documentation-with-swagger)
  - [Error Logging and Management](#error-logging-and-management)
  - [Features under development](#features-under-development)
- [3. Technologies and Dependencies](#3-technologies-and-dependencies)
- [4. Documentation](#4-documentation)
- [5. Main Endpoints](#5-main-endpoints)
  - [Films Endpoints](#films-endpoints)
  - [Person Endpoints](#person-endpoints)
  - [Reviews Endpoints](#reviews-endpoints)
  - [Scores Endpoints](#scores-endpoints)
- [6. Structure](#6-structure)
  - [Create Film](#create-film)
  - [Create Person](#create-person)
  - [Add Review](#add-review)
  - [Add Score](#add-score)
- [7. Installation and Execution](#7-installation-and-execution)
  - [Clone the Repository](#clone-the-repository)
  - [Import into IDE](#import-into-ide)
  - [Run the Application](#run-the-application)





## 1. Description

- FilmFeel is a Spring Boot service for managing and organizing a film catalog where users can interact through reviews and ratings. This API is designed for applications that aim to enrich user experience by offering features like movie ratings, detailed film information storage, and user profile management with distinct roles.

## 2. Key Features

- **Comprehensive Movie Management: **Create, modify, and delete movie records. Each film includes details such as title, year, duration, synopsis, team members/cast, and image path.

- ** User and Role System:** Includes an authentication and authorization system with two roles (USER and ADMIN). Administrators can manage users, assign roles, and perform other advanced operations.

- **User Interactions:** Users can rate and review each movie, which contributes to each film's average rating.

- **People-Film Association:** Allows assigning actors, directors, musicians, photographers, and screenwriters to movies, providing a richer context of the team involved in each project.

- **Interactive Documentation with Swagger:** The API is documented with Swagger, offering an interactive interface to test endpoints and view data schema in real-time.

- **Error Logging and Management:** Includes a detailed logging system for traceability and problem resolution during development and production environments.

*Features still under development*: 

	 Authentication and Authorization with Spring Security and JWT: Role configuration (USER and ADMIN) via Spring Security and JWT tokens for secure, stateless authentication. Users with the ADMIN role will have advanced permissions such as creating and managing profiles of other ADMIN role users.
	Advanced Logging: Incorporation of a detailed logging system at the service level, focused on precise monitoring and error auditing to improve traceability and diagnosis in production environments.
	Spring Batch: Implementation of Spring Batch to manage bulk data loading and efficient processing, optimizing the API's capacity to handle large volumes of information.


## 3. Technologies and dependencies

- **Java 17**
- **Spring Boot 3.3.4**
- **Swagger con OpenAPI **
- **H2 Database**
-  **MySQL Database**
- **Lombok**
- **Maven**

## 4. Documentation

Automatically generated with Swagger:

```html
http://localhost:8080/doc/swagger-ui/index.html
```


## 5. Main Endpoints


**Films (/api/peliculas)**


| Method | Endpoint  | Description |
| :------------ |:---------------:| -----:|
| GET | */api/peliculas* | List all films |
| POST | */api/peliculas/crear*  | Crear a new film|
| PUT | */api/peliculas/modificar/{id}* | Update an existing film|
| DELETE | */api/peliculas/eliminar/{id}*  | Delete a film by ID |


**Person (/api/personas)**


| Method | Endpoint  | Description |
| :------------ |:---------------:| -----:|
| GET | */api/personas/listar* | List all people |
| POST | */api/personas/crear*  | Create a new person
| POST | */api/personas/{filmId}/asociar-pelicula/{personId}* | Associate a person to a film
| DELETE | */api/personas/eliminar/{id}*  | Delete a person by ID|


**Reviews (/reviews)**

| Method | Endpoint  | Description|
| :------------ |:---------------:| -----:|
| GET | */reviews/film/{filmId}* |List all reviews for a film |
| POST | */reviews/film/{filmId}/nueva-review* | Add a review to a film
| DELETE | */reviews/borrar/{reviewId}* | Delete a review by ID


**Scores (/puntuaciones)**

| Método | Endpoint  | Description |
| :------------ |:---------------:| -----:|
| GET | */puntuaciones/film/{filmId}* |Get all scores for a film |
| POST | */puntuaciones/film/{filmId}/nueva-puntuación* | Add a score to a film
| DELETE | */puntuaciones/eliminar/{scoreId}* | Delete a score by ID
| GET | */puntuaciones/media/{filmId}* | Get the average rating for a film
----


## 6. Structure



### 1. **Create film**


#### - **Request *POST***:


To create a new film via Postman, make a POST request to /api/movies/create. This endpoint requires data in form-data, including an image file for the movie poster.

**a) Endpoint**

- URL: *http://localhost:8060/api/peliculas/crear*
- Method: POST
- Content Type: multipart/form-data

**b) Setup Instructions for Postman**

1. Select the **POST** method.
2. Enter the **endpoint URL**: *http://localhost:8080/api/peliculas/crear*
3. Go to the **Body** tab and select** form-data**.
4. Add the following fields in** form-data**:

| Key | Value  | Type |
| :------------ |:---------------:| -----:|
| title| *Film title* |Text|
| year | Release year  (e.g., 2022) | Text
| synopsis | *film synopsis* | Text
| duration | *Duration in minutes (e.g., 111)* | Text
| poster | *Film's poster image file* | File

> **Note: **Set the poster field as File and select an image file from your device.


Complete the fields as in the following example:

- *title: "Inception*
- *year: "2010"*
- *duration: "148"*
- *synopsis: "A mind-bending thriller about dream manipulation and inception."*
- *poster: [Select Image file]**

**Example Succesful Response**

If successful, the API responds with a message like:

`1 Película creada con éxito: Inception`


#### **- Response (JSON):**



```json
{
    "id": Long,
    "title": String,
    "year": Integer,
    "duration": Integer,
    "synopsis": String,
    "posterRoute": String,
    "migrate": Boolean,
    "dateMigrate": LocalDate,
    "portada": MultipartFile (o null),
    "photographer": {
        "id": Long,
        "name": String,
        "surname": String,
        "typePerson": Enum("ACTOR", "DIRECTOR", "GUIONISTA", etc.)
    },
    "scriptwriters": [
        {
            "id": Long,
            "name": String,
            "surname": String,
            "typePerson": Enum
        }
    ],
    "filmsMusicians": [
        {
            "id": Long,
            "name": String,
            "surname": String,
            "typePerson": Enum
        }
    ],
    "directors": {
        "id": Long,
        "name": String,
        "surname": String,
        "typePerson": Enum
    },
    "actors": [
        {
            "id": Long,
            "name": String,
            "surname": String,
            "typePerson": Enum
        }
    ],
    "userEntity": {
        "id": Long,
        "username": String,
        "name": String,
        "surname": String,
        "email": String
    },
    "reviews": [
        {
            "id": Long,
            "reviewTitle": String,
            "reviewText": String,
            "reviewDate": LocalDate,
            "userEntity": {
                "id": Long,
                "username": String,
                "name": String,
                "surname": String
            }
        }
    ],
    "scores": [
        {
            "id": Long,
            "value": Integer,
            "userEntity": {
                "id": Long,
                "username": String,
                "name": String,
                "surname": String
            }
        }
    ]
}

```



**POST requests from other entities in JSON format and the response:**

### 2. **Create Person**



#### - **Request *POST***:


```json
{
    "name": "Timothee",
    "surname": "Chalamet ",
    "typeEnum": "ACTOR"
}
```


```json
{
    "name": "Christopher",
    "surname": "Nolan ",
    "typeEnum": "DIRECTOR"
}
```


```json
{
    "name": "Emmanuel",
    "surname": "Lubezki",
    "typeEnum": "FOTOGRAFO"
}
```
```json
{
    "name": "Hans",
    "surname": "Zimmer ",
    "typeEnum": "MUSICO"
}
```

```json
{
    "name": "Nora",
    "surname": "Ephron ",
    "typeEnum": "GUIONISTA"
}
```




#### **- Response (JSON):**

```json
{
        "id": Long,
        "name": String,
        "surname": String,
        "typePerson": Enum
}
```

### 3. Add Review

#### - **Request *POST***:

```json
{
  "reviewTitle": "Amo esta película",
  "reviewText": "Es una de mis películas favoritas!"

}
``` 

#### **- Response (JSON):**

```json
{
    "id": Long,
    "reviewTitle": String,
    "reviewText": String,
    "reviewDate": LocalDate
}
```

### 4. Add Score

#### - **Request *POST***:

```json
{  
    
    "value": 7
  
}
```
#### **- Response (JSON):**

```json
{
    "id": Long,
    "value": Integer
}
```




## 7. Installation and Execution


**a) Clone the Repository**

**b) Import into the IDE**

To run the project, we recommend using* IntelliJ IDEA* or *Eclipse*.

c) **Run the Application**

Once imported, locate the main class (FilmFeelApiApplication.java) and run it as a Spring Boot application. The API will be available at http://localhost:8080


_______________________________________________
_______________________________________________

## Version Español

# FilmFeel API (en desarrollo)

> **Nota**: *Esta API REST está actualmente en una fase de desarrollo, por lo que algunas funcionalidades pueden estar en proceso de ajuste o expansión, y es posible que, de forma ocasional, se presenten errores. Se están realizando mejoras de forma continua para aumentar la estabilidad y añadir nuevas características.




## 1. Descripción

- FilmFeel es un servicio Spring Boot para administrar y organizar un catálogo de películas en el que los usuarios pueden interactuar mediante reseñas y calificaciones. Esta API está pensada para aplicaciones que buscan enriquecer la experiencia de sus usuarios al ofrecer funcionalidades como valoración de películas, almacenamiento de info detallada de cada film y gestión de perfiles de usuarios con perfiles diferenciados.

## 2. Características principales

- **Gestión completa de películas**: Creación, modificación y eliminación de registros de películas. Cada film incluye detalles como título, año, duración, sinopsis, miembros del equipo técnico /reparto y ruta a imagen. 
-**Sistema de usuarios y roles**: Incluye un sistema de autenticación y autorizaciñón con dos roles (USER y ADMIN).  Los administradores pueden gestionar usuarios, asignar roles y realizar otras operaciones avanzadas.
-** Interacciones de los usuarios:** Los usuarios pueden dejar una calificación y reseña por película que se reflejan en el promedio de cada film. 
- **Asociación de personas a películas**: Permite asignar actores, director, músicos, fotógrafo y guionistas a las películas. De esta manera, se proporciona un contexto más rico sobre el equipo involucrado en cada proyecto.
- **Documentación interactiva con Swagger**: La API está documentada con Swagger. Ofrece una interfaz interactiva donde se puede probar los endpoint y visualizar el esquema de datos en tiempo real.
- **Registro y manejo de errores**: Incluye un sistema de logs detallado que facilita la trazabilidad y resolución de problemas durante el desarrollo y en entornos de producción.

*Algunas de las caracteristicas aún por implementar*

	 Autenticación y Autorización con Spring Security y JWT: Configuración de roles (USER y ADMIN) mediante Spring Security y tokens JWT para la autenticación segura y sin estado. Los usuarios con rol ADMIN tendrán permisos avanzados como la creación y gestión de perfiles de otros usuarios con rol ADMIN.
	Logger Avanzado:  Incorporación de un sistema de logs detallados a nivel de servicio, enfocado en el monitoreo preciso y la auditoría de errores para mejorar la trazabilidad y el diagnóstico en entornos de producción.
	Spring Batch: Implementación de Spring Batch para gestionar la carga masiva de datos y su procesamiento eficiente, optimizando la capacidad de la API para manejar grandes volúmenes de información.


## 3. Tecnologías y dependencias

- **Java 17**
- **Spring Boot 3.3.4**
- **Swagger con OpenAPI **
- **H2 Database**
-  **MySQL Database**
- **Lombok**
- **Maven**

## 4. Documentación

Se genera automáticamente con Swagger y puede ser visualizada en la ruta:

```html
http://localhost:8080/doc/swagger-ui/index.html
```


## 5. Endpoints principales


**Películas (/api/peliculas)**


| Método | Endpoint  | Descripción |
| :------------ |:---------------:| -----:|
| GET | */api/peliculas* | Listar todas las películas |
| POST | */api/peliculas/crear*  | Crear una nueva película|
| PUT | */api/peliculas/modificar/{id}* | Modificar una película existente|
| DELETE | */api/peliculas/eliminar/{id}*  | Eliminar una película por ID |


**Personas (/api/personas)**


| Método | Endpoint  | Descripción |
| :------------ |:---------------:| -----:|
| GET | */api/personas/listar* | Listar todas las personas |
| POST | */api/personas/crear*  | Crear una nueva persona
| POST | */api/personas/{filmId}/asociar-pelicula/{personId}* | Asociar una persona a una película
| DELETE | */api/personas/eliminar/{id}*  | Eliminar una persona por ID |


**Reseñas (/reviews)**

| Método | Endpoint  | Descripción |
| :------------ |:---------------:| -----:|
| GET | */reviews/film/{filmId}* |Listar todas las reseñas de una película |
| POST | */reviews/film/{filmId}/nueva-review* | Añadir una reseña a una película
| DELETE | */reviews/borrar/{reviewId}* | Eliminar una reseña por ID


**Puntuaciones (/puntuaciones)**

| Método | Endpoint  | Descripción |
| :------------ |:---------------:| -----:|
| GET | */puntuaciones/film/{filmId}* |Obtener todas las puntuaciones de una película |
| POST | */puntuaciones/film/{filmId}/nueva-puntuación* | Añadir una puntuación a una película
| DELETE | */puntuaciones/eliminar/{scoreId}* | Eliminar una puntuación por ID
| GET | */puntuaciones/media/{filmId}* | Obtener la puntuación promedio de una película
----


## 6. Estructura de datos



### 1. **Crear película**


#### - **Solicitud *POST***:



Para crear una nueva película en la API desde Postman, realiza una solicitud POST al endpoint */api/peliculas/crear*. Este endpoint requiere enviar los datos en *form-data*, incluyendo un archivo de imagen para el póster de la película.

**a) Endpoint**

- URL: *http://localhost:8060/api/peliculas/crear*
- Método: POST
- Tipo de Contenido: multipart/form-data

**b) Instrucciones para configurar la solicitud en Postman**

1. Selecciona el método **POST**.
2. Introduce la URL del **endpoint**: *http://localhost:8060/api/peliculas/crear*
3. Navega a la pestaña **Body** y selecciona** form-data**.
4. Añade los siguientes campos en **form-data**

| Key | Value  | Tipo |
| :------------ |:---------------:| -----:|
| title| *Título de la película* |Text|
| year | Año de estreno (e.g., 2022) | Text
| synopsis | *Sinopsis breve de la película* | Text
| duration | *Duración en minutos (e.g., 111)* | Text
| poster | *archivo de imagen de la película* | File

> **Nota: **Configura el campo poster como File y selecciona un archivo de imagen desde tu equipo.


Debes completar los campos como en el ejemplo siguiente:

- *title: "Inception*
- *year: "2010"*
- *duration: "148"*
- *synopsis: "A mind-bending thriller about dream manipulation and inception."*
- *poster: [Selecciona el archivo de imagen]**

**Ejemplo de Respuesta Exitosa**

Si la solicitud se realiza correctamente, la API responderá con un mensaje similar a este:

`1 Película creada con éxito: Inception`


#### **- Respuesta (JSON):**



```json
{
    "id": Long,
    "title": String,
    "year": Integer,
    "duration": Integer,
    "synopsis": String,
    "posterRoute": String,
    "migrate": Boolean,
    "dateMigrate": LocalDate,
    "portada": MultipartFile (o null),
    "photographer": {
        "id": Long,
        "name": String,
        "surname": String,
        "typePerson": Enum("ACTOR", "DIRECTOR", "GUIONISTA", etc.)
    },
    "scriptwriters": [
        {
            "id": Long,
            "name": String,
            "surname": String,
            "typePerson": Enum
        }
    ],
    "filmsMusicians": [
        {
            "id": Long,
            "name": String,
            "surname": String,
            "typePerson": Enum
        }
    ],
    "directors": {
        "id": Long,
        "name": String,
        "surname": String,
        "typePerson": Enum
    },
    "actors": [
        {
            "id": Long,
            "name": String,
            "surname": String,
            "typePerson": Enum
        }
    ],
    "userEntity": {
        "id": Long,
        "username": String,
        "name": String,
        "surname": String,
        "email": String
    },
    "reviews": [
        {
            "id": Long,
            "reviewTitle": String,
            "reviewText": String,
            "reviewDate": LocalDate,
            "userEntity": {
                "id": Long,
                "username": String,
                "name": String,
                "surname": String
            }
        }
    ],
    "scores": [
        {
            "id": Long,
            "value": Integer,
            "userEntity": {
                "id": Long,
                "username": String,
                "name": String,
                "surname": String
            }
        }
    ]
}

```



**Solicitudes POST de otras entidades en formato JSON y lo que devuelve:**

### 2. **Crear Persona**



#### - **Solicitud *POST***:


```json
{
    "name": "Timothee",
    "surname": "Chalamet ",
    "typeEnum": "ACTOR"
}
```


```json
{
    "name": "Christopher",
    "surname": "Nolan ",
    "typeEnum": "DIRECTOR"
}
```


```json
{
    "name": "Emmanuel",
    "surname": "Lubezki",
    "typeEnum": "FOTOGRAFO"
}
```
```json
{
    "name": "Hans",
    "surname": "Zimmer ",
    "typeEnum": "MUSICO"
}
```

```json
{
    "name": "Nora",
    "surname": "Ephron ",
    "typeEnum": "GUIONISTA"
}
```




#### **- Respuesta (JSON):**

```json
{
        "id": Long,
        "name": String,
        "surname": String,
        "typePerson": Enum
}
```

### 3. Añadir reseña

#### - **Solicitud *POST***:

```json
{
  "reviewTitle": "Amo esta película",
  "reviewText": "Es una de mis películas favoritas!"

}
``` 

#### **- Respuesta (JSON):**

```json
{
    "id": Long,
    "reviewTitle": String,
    "reviewText": String,
    "reviewDate": LocalDate
}
```

### 4. Añadir puntuación

#### - **Solicitud *POST***:

```json
{  
    
    "value": 7
  
}
```
#### **- Respuesta (JSON):**

```json
{
    "id": Long,
    "value": Integer
}
```


## 7. Instalación y ejecución


**a) Clona el repositorio**

**b) Importar en el IDE**

Para ejecutar el proyecto, recomendamos usar* IntelliJ IDEA *o *Eclipse*

c) **Ejecuta la Aplicación**
Una vez importado, busca la clase principal (*FilmFeelApiApplication.java*) y ejecútala como una aplicación Spring Boot. La API estará disponible en http://localhost:8080


## **8. Consideraciones adicionales**

*Esta API REST está actualmente en una fase temprana de desarrollo, por lo que algunas funcionalidades pueden estar en proceso de ajuste o expansión, y es posible que ocasionalmente se presenten errores. Se están realizando mejoras de forma continua para aumentar la estabilidad y añadir nuevas características.*

