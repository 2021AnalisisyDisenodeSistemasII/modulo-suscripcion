# Modulo de Suscripción - StarBank

###### Repositorio para el módulo de Suscripción de Clientes, realizado por:

- [Diego Alejandro Poveda Alzate](https://github.com/diegopovalz)
- [Pedro Pablo Saldarriaga Jaramillo](https://github.com/pedrops26)

## Estructura del Proyecto Java
    StarBank
    ├── src  
    │   ├── main 
    │   │   ├── java/com/povsal/starbank
    │   │   │   ├── configuration               # Clases de Configuración
    │   │   │   ├── controller                  # Controladores para recibir peticiones REST
    │   │   │   ├── model                       # Clases de modelo del dominio
    │   │   │   ├── service                     # Servicios necesarios para ejecutar operaciones sobre DB y modelo
    │   │   │   └── utils                       # Utilidades extras necesarias para el funcionamiento de los JSON
    │   │   └── resources
    │   │       ├── static                      # JSONs necesarios para la ejecución
    │   │       └── application.properties      # Propiedades del Proyecto
    │   └── ...
    └── ...

## Cómo instalar
### Back-End
Se debe instalar [Maven](https://maven.apache.org/download.cgi) en su versión 3.6.3 o más reciente, debido a que el programa usa Spring Boot junto a otras dependencias.
En cuanto a la instalación, se recomienda seguir [los pasos dados por Maven](https://maven.apache.org/install.html).
Luego de haber instalado la herramienta, el proyecto se podrá ejecutar desde el IDE preferido

### Front-End
No se necesita ningún tipo de herramienta para poner a funcionar el Front-End, ya que fue hecho con HTML, CSS y JavaScript puro; junto con algunas librerías añadidas por CDN, lo cual significa que no es necesario el uso de Node.
