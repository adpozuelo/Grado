PRA2 - ICSD - EJERCICIO 1 - Antonio Díaz Pozuelo - adpozuelo@uoc.edu

Lo primero es añadir la tabla y los campos de prueba a la base de datos PostgreSQL.
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

Después, para compilar e implementar el ejercicio hay que ejecutar el comando "ant" desde el directorio raiz.
Obteniendo la siguiente salida:

Microsoft Windows XP [Versión 5.1.2600]
(C) Copyright 1985-2001 Microsoft Corp.

E:\PRA2\pra2_e1_src>ant
Buildfile: E:\PRA2\pra2_e1_src\build.xml

clean:
   [delete] Deleting directory E:\PRA2\pra2_e1_src\build
   [delete] Deleting directory E:\PRA2\pra2_e1_src\dist

init:
    [mkdir] Created dir: E:\PRA2\pra2_e1_src\build\jar
    [mkdir] Created dir: E:\PRA2\pra2_e1_src\build\war
    [mkdir] Created dir: E:\PRA2\pra2_e1_src\build\jar\META-INF
    [mkdir] Created dir: E:\PRA2\pra2_e1_src\build\war\WEB-INF
    [mkdir] Created dir: E:\PRA2\pra2_e1_src\build\war\WEB-INF\classes
    [mkdir] Created dir: E:\PRA2\pra2_e1_src\dist

compileEjb:
     [copy] Copying 1 file to E:\PRA2\pra2_e1_src\build\jar\META-INF
     [copy] Copying 1 file to E:\PRA2\pra2_e1_src\build\jar
    [javac] Compiling 3 source files to E:\PRA2\pra2_e1_src\build\jar

compileWar:
     [copy] Copying 25 files to E:\PRA2\pra2_e1_src\build\war
     [copy] Copied 4 empty directories to 1 empty directory under E:\PRA2\pra2_e
1_src\build\war
    [javac] Compiling 4 source files to E:\PRA2\pra2_e1_src\build\war\WEB-INF\cl
asses

jarEjb:
      [jar] Building jar: E:\PRA2\pra2_e1_src\dist\pra2.jar

deployClient:
      [jar] Building jar: E:\PRA2\pra2_e1_src\dist\pra2.war

ear:
     [copy] Copying 1 file to E:\PRA2\pra2_e1_src\dist\META-INF
      [jar] Building jar: E:\PRA2\pra2_e1_src\dist\pra2.ear

deployear:
     [copy] Copying 1 file to C:\jboss-as-7.1.1.Final\standalone\deployments

all:

BUILD SUCCESSFUL
Total time: 5 seconds

Finalmente, abrir el navegador web y conectarse a la dirección http://localhost:8080/pra2