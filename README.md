# MovieTime
Aplicación para mostrar información de Perfil de Actores, Peliculas, Subir fotos a servidor y mostrar Mapa.

## Empezar
¿Qué necesito para empezar a utilizar la aplicación de MovieTIme?
-clonar este repository y compilar la branch Master.
```
https://github.com/aldourtusuastegui/MovieTime.git
```

¿Qué necesito tener instalado?
```
Android Studio
```
```
Contar con un dispositivo físico o emulador 
```

## Preguntas frecuentes
#### ¿A partir de que versión de android es compatible MovieTime?
Api 21 (Android 5.0 LOLLIPOP)

#### ¿De donde obtiene la información MovieTime?

Este proyecto obtiene información relacionada con películas y personas mediante el uso de la API de The Movie Database (TMDb). A continuación, se detallan los puntos clave:

#### Fuente de Datos

La información se extrae de la API de The Movie Database (TMDb), accesible en https://www.themoviedb.org/.

#### Endpoints Utilizados

El proyecto utiliza varios endpoints proporcionados por la API de TMDb para obtener datos específicos:

##### Personas Populares:
- https://api.themoviedb.org/3/person/popular
##### Películas Mejor Valoradas:
- https://api.themoviedb.org/3/movie/top_rated
##### Películas Populares:
- https://api.themoviedb.org/3/movie/popular
##### Recomendaciones de Películas:
- https://api.themoviedb.org/3/movie/{movie_id}/recommendations

#### ¿Con que tecnologías se desarrolló MovieTime? 
- Kotlin
- Retrofit
- Gson
- Room
- Hilt
- Kotlin Flows
- LiveData
- Coroutines
- Mockk
- JUnit
- Google Maps
- Navigation
- Firebase Storage
- Glide
- MVVM
- Clean Architecture

#### ¿Como funciona la arquitectura de la aplicación? 

MovieTime cuenta con Clean Architecture and MVVM en donde tenemos diferentes features, los cuales estan divididos en su propio folder.

Cada feature cuenta con su propia logica y no comparte esta logica con los otros features, los features estan compuestos por tres capas principales que son :

- Data : Se encarga de obtener datos del servidor o base de datos local. (Retrofit,Room)
- Domain : Se encarga de la logica de negocio de la aplicación, Aqui tenemos Converters, UseCases, y diferentes Validaciones de la logica de negocio.
- Presentation : Se encarga de mostrar la información al Usuario, esta información la solicita el viewModel a los diferentes useCases.

### Estructura de Carpetas

### data
- **local**: Contiene la lógica y la implementación para la persistencia de datos a nivel local.
- **remote**: Contiene la lógica y la implementación para la obtención de datos desde una fuente remota.
- **models**: Almacena las definiciones de modelos de datos utilizados en el módulo.
- **repository**: Se encarga de la gestión y coordinación de los datos entre las fuentes locales y remotas.

### domain
- **entities**: Define las entidades principales utilizadas en el dominio del módulo.
- **repository**: Define las interfaces y contratos para los repositorios que se implementarán en el módulo.
- **usecases**: Contiene la lógica de casos de uso específicos de la funcionalidad del módulo.
- **conversion**: Incluye la lógica de conversión de datos entre las capas del dominio.

### presentation
- **MoviesFragment**: Contiene la lógica y la interfaz gráfica asociada a la visualización de películas.
- **MoviesViewModel**: Representa el ViewModel asociado al fragmento `MoviesFragment`.
- **MovieAdapter**: Implementa el adaptador para la visualización de la lista de películas en la interfaz gráfica.

### Features

#### Profile
Muestra información del actor más popular del momento asi como las peliculas en las que ha participado, al perder la conexión a internet se persisten los datos usando Room.
- Se uso Retrofit para obtener datos del servidor.
- Room para persistir la información.
- Se implementó clean architecture y MVVM.
- Se hace uso de Hilt para el manejo de dependencias.
- Se usa una sealed class para menejar los diferentes estados de la Respuesta del servidor.
- Se usaron LiveData, Flows y Coroutines.

#### Movies
Muestras diferentes listados de Peliculas (Populares,Mejor calificadas,Recomendaciones), al perder la conexión a internet se persisten los datos usando Room.
- Se uso Retrofit para obtener datos del servidor.
- Room para persistir la información.
- Se implementó clean architecture y MVVM.
- Se hace uso de Hilt para el manejo de dependencias.
- Se usa una sealed class para menejar los diferentes estados de la Respuesta del servidor.
- Se usaron LiveData, Flows y Coroutines.

#### Images
Da la posibilidad de seleccionar imagenes de la galeria del dispositivo, poder visualizar cada una de las imagenes, y subirlas a Firebase storage.
- Se usa una sealed class para menejar los diferentes estados de la Respuesta del servidor.
- Se implemento Firebase Storage.
- Se hace un intent para abrir la galeria.

#### Location
- Se agrego google maps al proyecto para poder mostrar el Mapa correctamente. 

#### ¿Qúe me hubiese gustado mejorar? 

#### Tab Location
Me hubiese gustado terminar la ultima parte relacionada con la localización. solo he logrado mostrar el mapa con google maps.
Para mi una posible solución a este problema seria la siguiente:
- Pedir al usuario permisos para poder seguir su localización.
- Hacer uso de un Service para obtener la ubicación del usuario cada x cantidad de tiempo.
- Hacer uso de firebase para guardar la localización del usuario.
- Obtener la información de firebase para mostrar diferentes marcadores en el mapa.

#### Tab Images
No alcancé a agregar la opción de capturar una foto con el dispositivo.
Una posible solución para este problema seria la siguiente:
- Pedir permisos al usuario para poder usar la camara.
- Crear un Intent para abrir la Camara.
- Agregar el Uri de la foto tomada a la lista de imagenes  que se van a subir.
- Tambien se podria guardar en el dispositivo, no solamente tomar el Uri.
- teniendo las imagenes seleccionadas y las que se tomaron desde la camara se hace la insercion a firebase dando click a Upload Images

#### En el codigo
- Algunas funciones podrian ser extension functions en vez de funciones normales.
- Las KEY de google maps y de la API podría haberla agregado a el archivo de properties.
- Mejorar el diseño en general de la aplicación.
- Usar Modulos en vez de packages para evitar tocar codigo de otros modulos por error.
- Agregar más unit tests.

#### UI

<img src="https://github.com/aldourtusuastegui/MovieTime/assets/13209412/62e42cd1-790e-439e-8152-1ea84f1860db"  width="300" height="600" />

<img src="https://github.com/aldourtusuastegui/MovieTime/assets/13209412/cb9c75d6-357d-4500-89bb-af1e31957cd0"  width="300" height="600" />

<img src="https://github.com/aldourtusuastegui/MovieTime/assets/13209412/f442e057-a6bd-426f-ad32-8270b2aab2a3"  width="300" height="600" />

<img src="https://github.com/aldourtusuastegui/MovieTime/assets/13209412/616b949b-4416-4a64-a1ef-7c3ab0f955fd"  width="300" height="600" />



    




