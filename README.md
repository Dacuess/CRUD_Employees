# CRUD_Employees
**Instrucciones para compilar y ejecutar la aplicacion:**
  1- Ejecutar Spring Boot Aplicacion desde Eclipse (Botón derecho sobre la clase CrudEmployeesMicroApplication.java -> Run As -> Spring Boot App.
  2- Se arrancará en el puerto 9000. Para ver el Swagger con la API hay que poner http://localhost:9000/swagger-ui/index.html# en el buscador

**Instrucciones para ejecutar los test:**
  1- Para ejecutar los test unitarios se puede hacer con el maven install o seleccionando el proyecto, boton derecho -> Run As -> Junit Test 

**Breve Explicacion de la estructura del proyecto:**
  Se ha establecido un controlador con todos los metodos acordados. Estos métodos llaman todos a una interfaz, la cual tiene definidos todos los métodos a implementar.
  Posteriormente se ha implementado dicha interfaz con la lógica de cada uno de los métodos, los cuales llaman mediante JPA a la Base de datos.

   CrudEmployeesController -> CrudEmployeesService -> CrudEmployeesServiceImpl -> TeammateRepository

**Cualquier decision de diseño o suposicion importante:**
  He tenido problemas para arrancar Kafka en local. Me he tenido que instalar Docker y ejecutar el comando "docker-compose up -d" para levantar los servicios
  incluidos en el docker-compose.yml, sin embargo a la hora de levantar el Zookeper aparecen los siguientes errores:
            ERROR Timed out waiting for connection to Zookeeper server
            ERROR Unable to resolve address: zookeeper:2181 
  Por lo tanto, me ha sido imposible arrancar la aplicacion con Kafka, ya que cuando lanzaba el mensaje desde el Producer, finalmente salia un timeout del Topic porque obviamente no estaba creado.
  Aunque no tendría que funcionar así, para que la funcionalidad funcionara y se creara un nuevo colaborador, he puesto un catch en el KafkaProducer.java para que cuando salte el timeout del Topic se llame al método del consumer que inserta el mensaje.
  Entiendo que el mensaje tendria que quedarse en el Topic y una vez el listener del Consumer lo escuchara se ejecutaría el código del consumer, por eso directamente he puesto directamente en el catch que se llame al método, ya que no tenia 
  disponible el servidor de Zookeper.
  El codigo de Kafka se encuentra en la rama feature/testKafka (https://github.com/Dacuess/CRUD_Employees/tree/feature/testKafka)
