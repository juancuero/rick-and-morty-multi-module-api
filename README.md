# Servicio Web de Rick and Morty

Este proyecto es un servicio web construido utilizando la Arquitectura Hexagonal (patrón de Puertos y Adaptadores) para interactuar con la [API de Rick and Morty](https://rickandmortyapi.com/documentation), almacenar datos en una base de datos MySQL y exponer dos recursos a través de endpoints: personajes y locaciones. El servicio está contenerizado utilizando Docker.

### Dominio (Core)
- **Entidades:** Definir las entidades principales, como Character y Location.
- **Repositorios:** Interfaces para acceder y almacenar datos.
- **Servicios:** Lógica de negocio relacionada con el dominio.

### Aplicación
- **Casos de Uso:** Implementaciones de la lógica específica de la aplicación para la gestión de personajes y locaciones.

### Infraestructura
- **Adaptadores de API:** Manejar las interacciones con la API de Rick and Morty.
- **Adaptadores de Persistencia:** Gestionar la persistencia de datos en la base de datos MySQL.

### Interfaz de Usuario (UI)
- **Controladores REST:** Exponer los endpoints para personajes y locaciones.

## Consumo de API Externa
El servicio consume la API de Rick and Morty para obtener datos sobre personajes y locaciones y los almacena en su propia base de datos MySQL.

## Endpoints
Los siguientes endpoints están disponibles para la gestión de personajes y locaciones:

### Personajes
- `GET /characters`: Obtener todos los personajes.
- `GET /characters/{id}`: Obtener un personaje por ID.
- `POST /characters`: Crear un nuevo personaje.
- `PUT /characters/{id}`: Actualizar un personaje existente.
- `DELETE /characters/{id}`: Eliminar un personaje.
- `POST /characters/sync`: Sincronizar información de los characters con la API oficial de Rick and Morty.

### Locaciones
- `GET /locations`: Obtener todas las locaciones.
- `GET /locations/{id}`: Obtener una locación por ID.
- `POST /locations`: Crear una nueva locación.
- `PUT /locations/{id}`: Actualizar una locación existente.
- `DELETE /locations/{id}`: Eliminar una locación.
- `POST /locations/sync`: Sincronizar información de los locations con la API oficial de Rick and Morty.

## Deploy con Docker
El archivo docker-compose.yml se encargará de crear automáticamente la base de datos MySQL y servirla en el puerto por defecto (3306) . Además, realizará el build del archivo .jar de nuestra aplicación y la servirá en http://localhost:8080.

Pasos para el Deploy
Asegúrate de tener Docker y Docker Compose instalados en tu sistema.
Ejecuta los siguientes comandos en el directorio raíz del proyecto:
```bash
docker-compose build
```

```bash
docker-compose up -d
```

## Ejemplo de Petición y Respuesta en JSON

### Sincronizar los characters
```bash
POST /characters/sync
```
![sync characters.](https://raw.githubusercontent.com/juancuero/rick-and-morty-multi-module-api/main/screenshots/sync-characters.png)

### Listar todos los characters
```bash
GET /characters
```
![list characters.](https://raw.githubusercontent.com/juancuero/rick-and-morty-multi-module-api/main/screenshots/list-characters.png)


### Sincronizar los locations
```bash
POST /locations/sync
```
![sync locations.](https://raw.githubusercontent.com/juancuero/rick-and-morty-multi-module-api/main/screenshots/sync-locations.png)

### Listar todos los locations
```bash
GET /locations
```
![list locations.](https://raw.githubusercontent.com/juancuero/rick-and-morty-multi-module-api/main/screenshots/list-locations.png)




 Puedes encontrarme en [LinkedIn](https://www.linkedin.com/in/juan-cuero-91342920b/).

