# Simple Store

------------------------------------------------------------------------------
DESCRIPCION / DESCRIPTION
------------------------------------------------------------------------------
# Español
Simple Store es una aplicacion web que permite la administracion de datos 
(Alta, Consulta, Modificacion y Baja), con las siguientes entidades:
- Producto
- Categoria

# English
Simple Store is a web application allows the management of data
(Create, Read, Update, Delete), with the next entities:
- Product
- Category

------------------------------------------------------------------------------
HERRAMIENTAS DE DESARROLLO / DEVELOPMENT TOOLS
------------------------------------------------------------------------------
# Español
Entre las herramientas que fueron utilizadas para el desarrollo del software
se encuentran:
- JDK 1.7.0_17
- PostgreSQL 9.2
- Apache Tomcat 7.0
- Apache Maven 3.3.3
- Spring 3.1.2
- Hibernate 4.0.0
- FreeMaker 2.3.15
- Bootstrap 3.3.6
- JQuery 2.1.4

# English
Between the development tools used for developing this software are:
- JDK 1.7.0_17
- PostgreSQL 9.2
- Apache Tomcat 7.0
- Apache Maven 3.3.3
- Spring 3.1.2
- Hibernate 4.0.0
- FreeMaker 2.3.15
- Bootstrap 3.3.6
- JQuery 2.1.4

------------------------------------------------------------------------------
PARTES DEL PROYECTO / PROJECT PARTS
------------------------------------------------------------------------------
# Español
Este repositorio contiene las siguientes partes:
- simple-store-server: Proyecto que funciona como servidor para gestionar solictudes usando REST.			  
- simple-store-client: Proyecto que funciona como cliente para realizar solictudes usando REST.

# English
- simple-store-server: Project that runs as server in order to handle request using REST.			  
- simple-store-client: Project that runs as client in order to build requests using REST.
						  
------------------------------------------------------------------------------
INSTRUCCIONES PARA LA EDICION, REVISION y EJECUCION DEL CODIGO /
INSTRUCTIONS FOR EDITING, REVIEWING AND EXECUTING THE CODE
------------------------------------------------------------------------------
# Español
1. Descarga los 2 proyectos, ambos proyectos fueron almacenados como proyectos
   del IDE eclipse, por lo tanto puedes descargarlos y colocarlos en tu work space
   si usas este IDE, sino, igual puedes revisar su contenido en cualquier otro IDE.

2. Debes crear la base de datos, para ello en un shell debes dirigirte al directorio
   `simple-store-server/bd/`. Si tu estas usando como sistema operativo una version
   de Windows puedes ejecutar el archivo `builDB.bat`, sino, ejecuta el siguiente 
   comando: `psql -U postgres -f script.sql`

3. Opcionalmente puedes insertar registros en la base de datos recien creada. Si quieres
   hacerlo puedes ejecutar el archivo `insertData.bat` si eres usuario de Windows, sino, 
   ejecuta el siguiente comando: `psql -U postgres -f data.sql` 

4. Carga ambos proyectos en tu IDE y luego debes modificar las credenciales del usuario 
   de PostgreSQL que te permitira conectarte a la BD recien creada. Para ello ubica el
   archivo `simple-store-server\src\main\resources\META-INF\persistence.xml` y coloca
   las credenciales correctas para tu caso.

5. Corre las 2 aplicaciones desde tu IDE usando un servidor de aplicaciones como 
   Apache Tomcat y en un navegador coloca la siguiente URL:
   `http://localhost:8080/simple-store-client/`

6. Interactua con la aplicacion.

# English
1. Download the 2 projects, both projects were stored as eclipse's project, 
   therefore you can download them and put them in your work space 
   if you use this IDE, else, you also can review their content in any other IDE.

2. You must ceate the database, then using a shell you must go to the directory
   `simple-store-server/bd/`. If you are usign as Operting System a version of 
   Windows you can execute the file `builDB.bat`, else, execute the next command:
   `psql -U postgres -f script.sql`

3. Optionally you can insert records into the database recent created. Whether you want
   do it you can execute the file `insertData.bat` if you are a Windows user, else, 
   you must execute the next command: `psql -U postgres -f data.sql` 
   
3. Opcionalmente puedes insertar registros en la base de datos recien creada. Si quieres
   hacerlo puedes ejecutar el archivo `insertData.bat` si eres usuario de Windows, sino, 
   ejecuta el siguiente comando: `psql -U postgres -f data.sql` 

4. Carga ambos proyectos en tu IDE y luego debes modificar las credenciales del usuario 
   de PostgreSQL que te permitira conectarte a la BD recien creada. Para ello ubica el
   archivo `simple-store-server\src\main\resources\META-INF\persistence.xml` y coloca
   las credenciales correctas para tu caso.

5. Corre las 2 aplicaciones desde tu IDE usando un servidor de aplicaciones como 
   Apache Tomcat y en un navegador coloca la siguiente URL:
   `http://localhost:8080/simple-store-client/`

6. Play with the application.

------------------------------------------------------------------------------
OBSERVACIONES
------------------------------------------------------------------------------
Como en la contruccion de este software se uso Maven, tu puedes desplegar 
estas aplicaciones en archivos war para que se ejecuten en Tomcat.
Para ello en el shell debes ir al direcorio donde se encuentran los proyectos
y ejecutar el siguiente comando: "mvn tomcat:deploy". Pero, previamente
tu debes tener configurado en tu maquina Maven con Tomcat.

Puedes leer mas acerca de esto en las siguientes paginas:
- http://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/
- http://kosalads.blogspot.com/2014/03/maven-deploy-war-in-tomcat-7.html
