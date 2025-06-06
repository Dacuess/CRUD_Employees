# CRUD_Employees (Funcionando con KAFKA)
**Instrucciones para compilar y ejecutar la aplicacion:**
  1- Arrancar Docker Desktop
  2- En la ruta del proyecto donde este el proyecto en local, abrir un cmd y ejecutar el comando: docker compose up -d
  2- Se arrancará en el puerto 9000. Para ver el Swagger con la API hay que poner http://localhost:9000/swagger-ui/index.html# en el buscador

**Instrucciones para ejecutar los test:**
  1- Para ejecutar los test unitarios se puede hacer con el maven test o seleccionando el proyecto, boton derecho -> Run As -> Junit Test 
  2- Los test de integracion se encuentran en la clase CrudEmployeesControllerTestIntegration.java y se ejecutan de la misma manera que los unitarios

**Breve Explicacion de la estructura del proyecto:**
  Se ha establecido un controlador con todos los metodos acordados. Estos métodos llaman todos a una interfaz, la cual tiene definidos todos los métodos a implementar.
  Posteriormente se ha implementado dicha interfaz con la lógica de cada uno de los métodos, los cuales llaman mediante JPA a la Base de datos.

   CrudEmployeesController -> CrudEmployeesService -> CrudEmployeesServiceImpl -> TeammateRepository

**Cualquier decision de diseño o suposicion importante:**
