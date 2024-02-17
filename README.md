# Proyecto GestionBibliotecaSpringBoot 🚀


## Integrantes del Grupo 👥

- 👨‍💻 Juan Manuel
- 👨‍💻 Sebastián
- 👨‍💻 Eric

<br>

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

La idea principal consiste en mantener el patron Observer como viene planteado de anteriores practicas, ya que la declaracion del metodo se ha mantenido sin alteraciones desde la practica hibernate. <br>
<br>

 ## Creación del Proyecto🛠️

 Iniciamos un proyecto usando IntelliJ, proyecto tipo Spring Initializr desde 0. Seleccionamos las dependencias para crear al API REST, luego añadimos las facetas JPA y Web, luego el run configuration y finalmente cargamos el contenedor Docker. <br>

<br>

 ## Configuracion del patrón de diseño MVC e implementación de modelo 🔦

 Luego de tener ya el proyecto creado y en marcha, creamos a mano el paquete `modelo`. En este paquete se contendra la información correspondiente a las entidades y los repositorios de las tablas. Todo ello agregando la faceta JPA sobre Hibernate y para acabar realizando el mapeo de las clases entidades o POJO de cuales empezaremos a partir.

 ### Mapeado de entidades

 Aquí en la imagen podemos apreciar como se han finalmente mapeado las entidades de la base de datos. (insertar imagen)
 Teniendo en cuenta la consideracion de que a ser posible en el caso de la tabla `prestamos` a la hora de unirla con la tabla libro y usuario para asi formar un prestamo, se referencien estas llaves foraneas en prestamo como un objeto o entidad y ahi mejorar la portabilidad de la clase y por ende la de la aplicación. <br>

<br>

## Implementación controlador 💅

Esta parte de la implementación se ha creado el paquete `controladores` que es lo que viene a ser la parte que contiene las clases directamente relacionadas con las tablas y <b>controlaran</b> como su nombre indica, el flujo de datos que pasan desde la vista o interfaz hacia la base de datos, basicamente seran validados y manejados con anotaciones propias del framework como <b>@Validated</b> o <b>@PostRequest</b> ... etc para dirigir las consultas y asegurar la persistencia en nuestros objetos. <br>

<br>


## Solicitudes HTTP :accessibility:
Esta es una clase la cual simplemente esta compuesta por 4 metodos estaticos, mas concretamente los metodos CRUD clasicos (create, read, update & delete). 

A continuacion una breve explicacion de la funcion para cada clase: <br>

#### deleteRequest(String pUrl) ↩️
Este método realiza una solicitud HTTP DELETE a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve true. Si no, lanza una excepción con un mensaje de error.

#### putRequest(String pUrl, String json) ↪️
Este método realiza una solicitud HTTP PUT a la URL proporcionada, enviando un objeto JSON como cuerpo de la solicitud. Si la respuesta del servidor es 200 (éxito), devuelve true. Si no, lanza una excepción con un mensaje de error.

#### getRequest(String pUrl) ⬇️
Este método realiza una solicitud HTTP GET a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve un JSONArray con la respuesta del servidor. Si no, lanza una excepción con un mensaje de error.

#### getRequestObject(String pUrl) ⤵️
Este método realiza una solicitud HTTP GET a la URL proporcionada. Si la respuesta del servidor es 200 (éxito), devuelve un JSONObject con la respuesta del servidor. Si no, lanza una excepción con un mensaje de error.

#### postRequest(String pUrl, String json) ⬆️
Este método realiza una solicitud HTTP POST a la URL proporcionada, enviando un objeto JSON como cuerpo de la solicitud. Si la respuesta del servidor es 200 (éxito), devuelve true. Si no, lanza una excepción con un mensaje de error. 

<br><br>

## Implementación clases DAO ⚛️

Aqui antes de empezar a programar para ser más pragmáticos decidimos dividir la parte front-end y back-end, la parte back-end tenemos lo habiamos comentado nuestra parte `modelo` (Entidades y repositorios) y `controlador`, en cambio en la parte front end conservar lo que habiamos implementado en la anterior practica, excepto los metodos DAO y las clases POJO de modelo.

<br>

### Parte Front-End

#### Clases + Interfaz DAO

Las interfaces se han mantenido, ya que la declaracion de los metodos son iguales

Y para la implementacion en este apartado hemos partido del metodo borrar que se encontraba en el PDF del tema 6, y que a su vez indagando descubrimos que este usaba los metodos de la clase HTTPRequest. Sucesivamente fuimos haciendo que todos los metodos de nuestras clases DAO fuera haciendo uso de estos metodos para automatizar la devolucion de objetos JSON e ir posteriormente desgranandolos. <br>

Comentar que para los metodos OR, hay una funcionalidad y es que Spring tiene unos metodos pre-definidos por ejemplo: `findByNombreOrAutorOrEditorial` y automaticamente va seleccionando que en el caso de libro sea por nombre o autor o ... lo que declaremos para no tener que hacerlo manualmente y dando el metodo directamente como resultado una lista del objeto segun sea.

#### Observer

Aqui directamente tampoco ha habido modificaciones ya que no era necesario. Ya que cuando una funcion termina de hacer su labor se activa el observer al igual que con la practica de hibernate para notificar y avisar.

#### Pojos

Aqui simplemente creamos un pojo por cada tabla de la base de datos y con atributo por campo de tabla, a parte con sus getters y sus setters y demas metodos autogenerados, toString y la novedad es que empleamos un metodo toJSONObject donde se usa el metodo put para ir añadiendo objetos de cada tabla. Luego estos seran usados en la implementacion.

<br>

### Parte Back-End

Ya se ha especificado en los apartados anteriores a <b>Implementacion clases DAO</b>

<br>

## Problemas Encontrados y Soluciones Aportadas 🚧

· Configuración del end-point en la cabezera de las clases controlador, en el @RequestMapping para que funcionase bien tuvimos que añadirlo asi, para libro por ejemplo: <b> "/api-rest/libros" </b>  <br><br>
· Interpretación por parte de Spring Boot a la hora de tomar los nombres de los elementos de la tabla, un problema de sintaxis con la anotacíon CamelPath, para solventarlo tuvimos que añadir la siguiente linea en el application.properties:  <br>
 <u> spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl </u><br><br>
· Problemas a la hora de realizar el where OR en la interfaz, habia que filtrar previamente en el controlador de libro si la categoria existia o no para obtener el resultado deseado<br><br>
· Problemas relacionados con el uso de los metodos getRequest y el tipo de objeto que estos devolvian...

<br>

## Recursos Utilizados 🏗️
 · PDF's de Antonio




 


 

