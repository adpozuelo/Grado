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
