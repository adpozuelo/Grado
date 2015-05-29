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

insert into pra2.category(id, name, description) values (1,'Rock','Eventos sobre m�sica rock');
insert into pra2.category(id, name, description) values (2,'Pop','Eventos sobre m�sica pop');
insert into pra2.category(id, name, description) values (3,'Heavy','Eventos sobre m�sica heavy');
insert into pra2.category(id, name, description) values (4,'Jazz','Eventos sobre m�sica Jazz');
insert into pra2.category(id, name, description) values (5,'Rumba','Eventos sobre m�sica Rumba');
insert into pra2.category(id, name, description) values (6,'Flamenco','Eventos sobre m�sica Flamenca');
