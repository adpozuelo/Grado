PRA2 - ICSD - EJERCICIO 2 - Antonio Díaz Pozuelo - adpozuelo@uoc.edu

** Servidor ** Directorio "server" **

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

Después, para compilar e implementar el ejercicio hay que ejecutar el comando "ant" desde el directorio raiz ("server").
Obteniendo la siguiente salida:

Microsoft Windows XP [Versión 5.1.2600]
(C) Copyright 1985-2001 Microsoft Corp.

E:\PRA2\pra2_e2_src\server>ant
Buildfile: E:\PRA2\pra2_e2_src\server\build.xml

clean:
   [delete] Deleting directory E:\PRA2\pra2_e2_src\server\build
   [delete] Deleting directory E:\PRA2\pra2_e2_src\server\dist

init:
    [mkdir] Created dir: E:\PRA2\pra2_e2_src\server\build\jar
    [mkdir] Created dir: E:\PRA2\pra2_e2_src\server\build\war
    [mkdir] Created dir: E:\PRA2\pra2_e2_src\server\build\soap
    [mkdir] Created dir: E:\PRA2\pra2_e2_src\server\build\jar\META-INF
    [mkdir] Created dir: E:\PRA2\pra2_e2_src\server\build\war\WEB-INF
    [mkdir] Created dir: E:\PRA2\pra2_e2_src\server\build\war\WEB-INF\classes
    [mkdir] Created dir: E:\PRA2\pra2_e2_src\server\dist

compileEjb:
     [copy] Copying 1 file to E:\PRA2\pra2_e2_src\server\build\jar\META-INF
     [copy] Copying 1 file to E:\PRA2\pra2_e2_src\server\build\jar
    [javac] Compiling 6 source files to E:\PRA2\pra2_e2_src\server\build\jar

compileWar:
     [copy] Copying 25 files to E:\PRA2\pra2_e2_src\server\build\war
     [copy] Copied 4 empty directories to 1 empty directory under E:\PRA2\pra2_e
2_src\server\build\war
    [javac] Compiling 4 source files to E:\PRA2\pra2_e2_src\server\build\war\WEB
-INF\classes

jarEjb:
      [jar] Building jar: E:\PRA2\pra2_e2_src\server\dist\pra2.jar

deployClient:
      [jar] Building jar: E:\PRA2\pra2_e2_src\server\dist\pra2.war

ear:
     [copy] Copying 1 file to E:\PRA2\pra2_e2_src\server\dist\META-INF
      [jar] Building jar: E:\PRA2\pra2_e2_src\server\dist\pra2.ear

deployear:
     [copy] Copying 1 file to C:\jboss-as-7.1.1.Final\standalone\deployments

all:

BUILD SUCCESSFUL
Total time: 3 seconds

** Cliente ** Directorio "client" **

Tras la compilacion e implementacion del servidor, se deben seguir los siguientes pasos:
1.- Importar el proyecto en Eclipse.
2.- Generar las clases que necesita el cliente mediante wsimport. Para ello y desde el proyecto de Eclipse, sobre build.xml -> botón derecho -> Run As -> 2 Ant Build.
3.- Acutalizar el proyecto de Eclipse. Para ello y desde Eclipse, sobre el proyecto de Eclipse -> botón derecho -> Refresh.
4.- Ejecutar el cliente SOAP. Para ello y sobre el proyecto de Eclipse -> botón derecho -> Run As -> 3 Java Application.
 
