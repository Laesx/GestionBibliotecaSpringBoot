# Proyecto GestionBibliotecaSpringBoot 🚀


## Integrantes del Grupo 👥

- 👨‍💻 Juan Manuel Sújar González
- 👨‍💻 Sebastián Olea Castillo
- 👨‍💻 Eric

## Objetivo del Trabajo 🎯
**1. Gestión de una base de datos, basada en una biblioteca usando SpringBoot.**

El objetivo de esta interesante práctica es aprovechar la facilidad y portabilidad que nos ofrece el framework de Spring Boot (extension de Spring) para crear y manejar clases que esten asociadas a tablas en una base de datos, todo ello en un 
periodo de tiempo muy inferior a como resultaría utilizando un modelo JPA o SQL convencional.

***Ventajas de usar Spring Boot:*** <br>
    - Simplifica y acelera el desarrollo de aplicaciones Java. <br>
    - Está diseñado para crear aplicaciones autónomas con una configuración mínima. <br> 
    - Autoconfiguración: Proporciona autoconfiguración inteligente basada en las dependencias presentes en el proyecto. <br> 
    - Desarrollo Rápido: Facilita la creación de aplicaciones con un rápido desarrollo y despliegue <br>
    ....... 

<br><br>
**2. Mantener el modelo MVC (modelo-vista-controlador).**

Podemos aprovechar la potencia y el resultado de la devolución de objetos por parte del uso de los controladores en conjunto con los metodos que se basan en las solicitudes HTTP para asi mantener la integridad del MVC
sin muchas alteraciones, asegurando así una arquitectura organizada y sobre todo escalable.

**3. Patrón Observer.**

La idea principal consiste en mantener el patron Observer como viene planteado de anteriores practicas, ya que lo que será devuelto por la parte del modelo se vera igual que si se usara un mòdelo JPA.

[//]: # (Juanma)
 ## Creación del Proyecto🛠️

 Iniciamos un proyecto usando IntelliJ, proyecto tipo Spring Initializr desde 0. Seleccionamos las dependencias para crear al API REST, luego añadimos las facetas JPA y Web, luego el run configuration y finalmente cargamos el contenedor Docker.

 ## Configuracion del patrón de diseño MVC e implementación de modelo

 Luego de tener ya el proyecto creado y en marcha, creamos a mano el paquete `modelo`. En este paquete se contendra la información correspondiente a las entidades y los repositorios de las tablas. Todo ello agregando la faceta JPA sobre Hibernate y para acabar realizando el mapeo de las clases entidades o POJO de cuales empezaremos a partir.

 ### Mapeado de entidades 🔦

 Aquí en la imagen podemos apreciar como se han finalmente mapeado las entidades de la base de datos. (insertar imagen)
 Teniendo en cuenta la consideracion de que a ser posible en el caso de la tabla `prestamos` a la hora de unirla con la tabla libro y usuario para asi formar un prestamo, se referencien estas llaves foraneas en prestamo como un objeto o entidad y ahi mejorar la portabilidad de la clase y por ende la de la aplicación.

## Implementación controlador 💅

Esta parte de la implementación se ha creado el paquete `controladores` que es lo que viene a ser la parte que contiene las clases directamente relacionadas con las tablas y <b>controlaran</b> como su nombre indica, el flujo de datos que pasan desde la vista o interfaz hacia la base de datos, basicamente seran validados y manejados con anotaciones propias del framework como <b>@Validated</b> o <b>@PostRequest</b> ... etc para dirigir las consultas y asegurar la persistencia en nuestros objetos.

## Solicitudes HTTP
Esta es una clase la cual simplemente esta compuesta por 4 metodos estaticos, mas concretamente los metodos CRUD clasicos (create, read, update & delete). 

A continuacion una breve explicacion de la funcion para cada clase: <br>

#### <b>deleteRequest(String pUrl)</b>
Este método realiza una solicitud HTTP DELETE a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve true. Si no, lanza una excepción con un mensaje de error.

#### <b>putRequest(String pUrl, String json)</b>
Este método realiza una solicitud HTTP PUT a la URL proporcionada, enviando un objeto JSON como cuerpo de la solicitud. Si la respuesta del servidor es 200 (éxito), devuelve true. Si no, lanza una excepción con un mensaje de error.

#### <b>getRequest(String pUrl)</b>
Este método realiza una solicitud HTTP GET a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve un JSONArray con la respuesta del servidor. Si no, lanza una excepción con un mensaje de error.

#### <b>getRequestObject(String pUrl)</b>
Este método realiza una solicitud HTTP GET a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve un JSONObject con la respuesta del servidor. Si no, lanza una excepción con un mensaje de error.

#### <b>postRequest(String pUrl, String json)</b>
Este método realiza una solicitud HTTP POST a la URL proporcionada, enviando un objeto JSON como cuerpo de la solicitud. Si la respuesta del servidor es 200 (éxito), devuelve true. Si no, lanza una excepción con un mensaje de error. 







 


 

