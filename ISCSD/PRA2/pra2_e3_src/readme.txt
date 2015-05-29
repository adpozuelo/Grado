PRA2 - ICSD - EJERCICIO 3 - Antonio Díaz Pozuelo - adpozuelo@uoc.edu

Lo primero es añadir la tabla y los campos de prueba a la base de datos PostgreSQL. Si este paso ya se hizo en el ejercicio 1, se puede obviar.
Para ello hay que ejecutar las sentencias SQL contenidas en el fichero sql_init.sql
Las sentencias SQL son las siguientes:

CREATE SCHEMA pra2 AUTHORIZATION "USER";

CREATE TABLE pra2.category
(
id integer NOT NULL,
"name" text NOT NULL,
description text,
CONSTRAINT "Category_pkey" PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE pra2.category owner TO "USER";

insert into pra2.category(id, name, description) values (1,'Rock','Eventos sobre música rock');
insert into pra2.category(id, name, description) values (2,'Pop','Eventos sobre música pop');
insert into pra2.category(id, name, description) values (3,'Heavy','Eventos sobre música heavy');
insert into pra2.category(id, name, description) values (4,'Jazz','Eventos sobre música Jazz');
insert into pra2.category(id, name, description) values (5,'Rumba','Eventos sobre música Rumba');
insert into pra2.category(id, name, description) values (6,'Flamenco','Eventos sobre música Flamenca');

Finalmente, para compilar e implementar el ejercicio hay que ejecutar el comando "start.bat" desde el directorio raíz.
Una vez ejecutado el script anterior, se abrirán tres consolas: una para el rmiregistry, una para el servidor RMI y otra para el cliente RMI.

NOTA: El fichero start.bat contiene dos lineas que ejecutan el comando ping dado que:
Si no se establece una pausa entre la ejecucion de rmiregistry y la del servidor y entre la del servidor y la del cliente, el cliente genera un error
de RMI dado que no se puede conectar con el servidor a traves de rmiregistry. Para solucionar este problema de encabalgamiento de ejecuciones he optado
por esta solucion (ping) ya que no requiere instalar ningun programa extra en Windows XP y es la mejor solucion que he encontrado en Internet.

Si se ejecutan los comandos del script "start.bat" a mano y uno a uno no hay problema, pero el script es más comodo.
