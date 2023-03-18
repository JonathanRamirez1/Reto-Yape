# Reto-Yape

- Se aplicó Clean Architecture para la separación del proyecto por capas, 4 en total "data" (Procesamiento de los datos),
  "di" (Inyección de dependencias con Dagger Hilt), "domain" (Logica de negocio, se decide de que data source se tomaran
  los datos, si del remote o del local), "ui" (todo lo que tenga que ver con la interfaz de usuario; activity, fragment, adapter)
  
- Implementación del patron de diseño MVVM, se basa en tener un viewmodel; la ui solicita información al viewmodel, el viewmodel
  a su vez le solicita información a la data y esta se la devuelve, al  devolverla, en el viewmodel nos subscribimos a un evento
  con LiveData, que va a ser la encargada de la comunicación viewmodel-actitvity/fragment, en el activity/fragment estaremos
  "oyendo" este evento con un Observer, la cual actualiza la ui.
  
- Para el debido funcionamiento de la app en la pantalla del Mapa de la receta, se requiere un API_KEY de Google, esta misma se declara
  en el archivo "local.properties" (MAPS_API_KEY=TU_API_KEY) como una variable de entorno y se usa en el Manifest.

### Tecnologías usadas

- 👉 Principios SOLID
- 👉 Clean Architecture
- 👉 Navigation Component
- 👉 Patrón MVVM
- 👉 Architectural Components
- 👉 Coroutines
- 👉 Persistencia de datos con Room
- 👉 Inyección de dependencias con Dagger Hilt
- 👉 Consumo del API con Retrofit
- 👉 Gson
- 👉 Unit Test
- 👉 viewBinding
- 👉 Coil
- 👉 Clean Code
- 👉 Manejo de estados
- 👉 Versionamiento del código con GIT
