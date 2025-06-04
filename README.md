# CRUD_Employees
*Instrucciones para compilar y ejecutar la aplicacion:*
  1- Ejecutar Spring Boot Aplicacion desde Eclipse (Botón derecho sobre la clase CrudEmployeesMicroApplication.java -> Run As -> Spring Boot App.
  2- Se arrancará en el puerto 9000. Para ver el Swagger con la API hay que poner http://localhost:9000/swagger-ui/index.html# en el buscador

*Instrucciones para ejecutar los test:*
  1- Para ejecutar los test unitarios se puede hacer con el maven install o seleccionando el proyecto, boton derecho -> Run As -> Junit Test 

*Breve Explicacion de la estructura del proyecto:*
  Se ha establecido un controlador con todos los metodos acordados. Estos métodos llaman todos a una interfaz, la cual tiene definidos todos los métodos a implementar.
  Posteriormente se ha implementado dicha interfaz con la lógica de cada uno de los métodos, los cuales llaman mediante JPA a la Base de datos.

   CrudEmployeesController -> CrudEmployeesService -> CrudEmployeesServiceImpl -> TeammateRepository

*Cualquier decision de diseño o suposicion importante:*
  No se ha podido realizar con Kafka porque el topic no me salia creado y al hacer la peticion con el producer saltaba un timeout. 
  He revisado temas de configuracion pero no he podido ver nada, es la primera vez que trabajo con Kafka, supongo que tendria que haber instalado algo de Kafka o del Zookeper, pero no he podido.  
