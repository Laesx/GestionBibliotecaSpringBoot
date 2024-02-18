# Proyecto Gestión BibliotecaSpringBoot 🚀


## Integrantes del Grupo 👥

- 👨‍💻 Juan Manuel
- 👨‍💻 Sebastián
- 👨‍💻 Eric

<br>

## Objetivo del Trabajo 🎯
**1. Gestión de una base de datos, basada en una biblioteca usando SpringBoot.**

El objetivo de esta interesante práctica es aprovechar la facilidad y portabilidad que nos ofrece el framework de Spring Boot (extension de Spring) 
para crear y manejar clases que están asociadas a tablas en una base de datos, todo ello en un 
periodo de tiempo muy inferior a como resultaría utilizando un modelo JPA o SQL convencional.

***Ventajas de usar Spring Boot:*** <br>
    - Simplifica y acelera el desarrollo de aplicaciones Java. <br>
    - Está diseñado para crear aplicaciones autónomas con una configuración mínima. <br> 
    - Autoconfiguración: Proporciona autoconfiguración inteligente basada en las dependencias presentes en el proyecto. <br> 
    - Desarrollo Rápido: Facilita la creación de aplicaciones con un rápido desarrollo y despliegue. <br>
  
<br><br>

**2. Mantener el modelo MVC (modelo-vista-controlador).**

Podemos aprovechar la potencia y el resultado de la devolución de objetos por parte del uso de los controladores en conjunto con los métodos que se basan en las solicitudes 
HTTP para asi mantener la integridad del MVC
sin muchas alteraciones, asegurando así una arquitectura organizada y sobre todo escalable.

**3. Patrón Observer.**

La idea principal consiste en mantener el patron Observer como viene planteado de anteriores prácticas,
ya que la declaración del método se ha mantenido sin alteraciones desde la práctica hibernate. <br>
<br>

 ## Creación del Proyecto🛠️

 Iniciamos un proyecto usando IntelliJ, proyecto tipo Spring Initializr desde 0.
 Seleccionamos las dependencias para crear la API REST, luego añadimos las facetas JPA y Web, luego el run configuration y finalmente cargamos el contenedor Docker. <br>

<br>

 ## Configuración del patrón de diseño MVC e implementación de modelo 🔦

 Una vez configurado el proyecto inicial, creamos a mano el paquete `modelo`. 
 En este paquete contiene la información correspondiente a las entidades y los repositorios de las tablas. 
 Todo ello agregando la faceta JPA sobre Hibernate y para acabar realizando el mapeo de las clases entidades o POJO de cuales empezaremos a partir.

 ### Mapeado de entidades

 Aquí en la imagen podemos apreciar como se han finalmente mapeado las entidades de la base de datos. (insertar imagen)
 Teniendo en cuenta la consideración de que a ser posible en el caso de la tabla `prestamos` a la hora de unirla con la tabla libro y usuario para asi formar un préstamo,
 se referencien estas llaves foráneas en préstamo como un objeto o entidad y ahi mejorar la portabilidad de la clase y por ende la de la aplicación. <br>

<br>

## Implementación controlador 💅

Esta parte de la implementación se ha creado el paquete `controladores` que es lo que viene a ser la parte que contiene las clases directamente relacionadas con las tablas y
<b>controlaran</b> como su nombre indica, el flujo de datos que pasan desde la vista o interfaz hacia la base de datos, básicamente serán validados y manejados con anotaciones
propias del framework como <b>@Validated</b> o <b>@PostRequest</b> para dirigir las consultas y asegurar la persistencia en nuestros objetos. <br>

<br>


## Solicitudes HTTP
Esta es una clase la cual simplemente está compuesta por 4 métodos estáticos, más concretamente los métodos CRUD clásicos (Create, Read, Update & Delete). 

A continuación una breve explicación de la función para cada clase: <br>

#### deleteRequest(String pUrl) ↩️
Este método realiza una solicitud HTTP DELETE a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve true.
Si no, lanza una excepción con un mensaje de error.

#### putRequest(String pUrl, String json) ↪️
Este método realiza una solicitud HTTP PUT a la URL proporcionada, enviando un objeto JSON como cuerpo de la solicitud. 
Si la respuesta del servidor es 200 (éxito), devuelve true. Si no, lanza una excepción con un mensaje de error.

#### getRequest(String pUrl) ⬇️
Este método realiza una solicitud HTTP GET a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve un JSONArray con la respuesta del servidor.
Si no, lanza una excepción con un mensaje de error.

#### getRequestObject(String pUrl) ⤵️
Este método realiza una solicitud HTTP GET a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve un JSONObject con la respuesta del servidor.
Si no, lanza una excepción con un mensaje de error.

#### postRequest(String pUrl, String json) ⬆️
Este método realiza una solicitud HTTP POST a la URL proporcionada, enviando un objeto JSON como cuerpo de la solicitud. Si la respuesta del servidor es 200 (éxito),
devuelve true. Si no, lanza una excepción con un mensaje de error. 

<br><br>

## Implementación clases DAO ⚛️

Aquí antes de empezar a programar para ser más pragmáticos decidimos dividir la parte front-end y back-end, la parte back-end tenemos lo habíamos comentado nuestra
parte `modelo` (Entidades y repositorios) y `controlador`, en cambío en la parte front end conservar lo que habíamos implementado en la anterior práctica, excepto 
los métodos DAO y las clases POJO de modelo.

<br>

### Parte Front-End

#### Clases + Interfaz DAO

Las interfaces se han mantenido, ya que la declaración de los métodos son iguales

Y para la implementación en este apartado hemos partido del método borrar que se encontraba en el PDF del tema 6, y que a su vez indagando descubrimos que este usaba los métodos de la clase HTTPRequest.
Sucesivamente fuimos haciendo que todos los métodos de nuestras clases DAO fuera haciendo uso de estos métodos para automatizar la devolución de objetos JSON e ir posteriormente desgranándolos. <br>

Cabe comentar que para los métodos OR, hay una funcionalidad y es que Spring tiene unos métodos pre-definidos por ejemplo: `findByNombreOrAutorOrEditorial` y automáticamente va seleccionando que en el
caso de libro sea por nombre o autor o ... lo que declaremos para no tener que hacerlo manualmente y dando el método directamente como resultado una lista del objeto según sea.

#### Observer

Aquí directamente tampoco ha habido modificaciones. Ya que cuando una función termina de hacer su labor se activa el observer al igual que con la práctica de hibernate para notificar y avisar.

#### Pojos

Para ello, simplemente creamos un pojo por cada tabla de la base de datos con sus atributos por cada campo de la tabla y demás métodos autogenerados, toString y la novedad es que empleamos un método
toJSONObject donde se usa el método put para ir añadiendo objetos de cada tabla. Luego estos serán usados en la implementación.

<br>

### Parte Back-End

Ya se ha especificado en los apartados anteriores a <b>Implementación clases DAO</b>

<br>

## Problemas Encontrados y Soluciones Aportadas 🚧

· Configuración del end-point en la cabecera de las clases controlador, en el @RequestMapping para que funcionase bien tuvimos que añadirlo asi, para libro por ejemplo: <b> "/api-rest/libros" </b>. <br><br>
· Interpretación por parte de Spring Boot a la hora de tomar los nombres de los elementos de la tabla, un problema de sintaxis con la anotación CamelPath, para solventarlo tuvimos que añadir la siguiente
línea en el application.properties: <br>
<u>spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl </u><br><br>
· Problemas a la hora de realizar el whereOR en la interfaz, había que filtrar previamente en el controlador de libro si la categoría existía o no para obtener el resultado deseado<br><br>
· Problemas relacionados con el uso de los métodos getRequest y el tipo de objeto que estos devolvían...

<br>

## Recursos Utilizados 🏗️
 · PDF's de Antonio




 


 

