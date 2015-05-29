INSERT INTO users(
            role, nif, active, city, country, state, street, zipcode, email, 
            name, password, language, surname)
    VALUES ('U', 54321, TRUE, 'Madrid','Madrid', 'España', 'Sol', '28022', 'plopez@uoc.edu', 
            'Pepe', 'abf8412b7c606f8acd8b58968e9b4733', 'SPANISH', 'Lopez');

INSERT INTO categories(
            id, description, name, superuser_id)
    VALUES (1, 'Categoria sobre fultbol', 'Futbol', '1');

INSERT INTO categories(
            id, description, name, superuser_id)
    VALUES (2, 'Categoria sobre Baloncesto', 'Baloncesto', '1');

INSERT INTO companies (
            id, name, superuser_id)
    VALUES (1, 'Empresa 1' , '1');

INSERT INTO companies (
            id, name, superuser_id)
    VALUES (2, 'Empresa 2' , '1');

INSERT INTO events(
            id, city, country, state, street, zipcode, description, enddate, 
            initdate, name, picturedata, picturedatatype, company_id, superuser_id)
    VALUES (1, 'Madrid', 'Madrid', 'España', 'Paseo de la Castellana', '28046', 'Final entre Real Madrid y Milan', '2015-12-29 22:10:02.999', 
            '2015-12-28 22:10:01.999', 'Final Eurocopa', '', 'image/jpeg', 1, '1');

INSERT INTO events(
            id, city, country, state, street, zipcode, description, enddate, 
            initdate, name, picturedata, picturedatatype, company_id, superuser_id)
    VALUES (2, 'Madrid', 'Madrid', 'España', 'Goya', '28046', 'Final entre Real Madrid y Panatinaikos', '2015-12-29 22:10:02.999', 
            '2015-12-28 22:10:01.999', 'Play-offs', '', 'image/jpeg', 1, '1');

INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (1, 'futbol');
    
INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (1, 'partido');

    INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (2, 'baloncesto');
    
INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (2, 'partido');
INSERT INTO users(
            role, nif, active, city, country, state, street, zipcode, email, 
            name, password, language, surname)
    VALUES ('U', 54321, TRUE, 'Madrid','Madrid', 'España', 'Sol', '28022', 'plopez@uoc.edu', 
            'Pepe', 'abf8412b7c606f8acd8b58968e9b4733', 'SPANISH', 'Lopez');

INSERT INTO categories(
            id, description, name, superuser_id)
    VALUES (1, 'Categoria sobre fultbol', 'Futbol', '1');

INSERT INTO categories(
            id, description, name, superuser_id)
    VALUES (2, 'Categoria sobre Baloncesto', 'Baloncesto', '1');

INSERT INTO companies (
            id, name, superuser_id)
    VALUES (1, 'Empresa 1' , '1');

INSERT INTO companies (
            id, name, superuser_id)
    VALUES (2, 'Empresa 2' , '1');

INSERT INTO events(
            id, city, country, state, street, zipcode, description, enddate, 
            initdate, name, picturedata, picturedatatype, company_id, superuser_id)
    VALUES (1, 'Madrid', 'Madrid', 'España', 'Paseo de la Castellana', '28046', 'Final entre Real Madrid y Milan', '2015-12-29 22:10:02.999', 
            '2015-12-28 22:10:01.999', 'Final Eurocopa', '', 'image/jpeg', 1, '1');

INSERT INTO events(
            id, city, country, state, street, zipcode, description, enddate, 
            initdate, name, picturedata, picturedatatype, company_id, superuser_id)
    VALUES (2, 'Madrid', 'Madrid', 'España', 'Goya', '28046', 'Final entre Real Madrid y Panatinaikos', '2015-12-29 22:10:02.999', 
            '2015-12-28 22:10:01.999', 'Play-offs', '', 'image/jpeg', 1, '1');

INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (1, 'futbol');
    
INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (1, 'partido');

    INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (2, 'baloncesto');
    
INSERT INTO event_keyword(
            event_id, keyword)
    VALUES (2, 'partido');
    
INSERT INTO events_categories(
            category_id, event_id)
    VALUES (1, 1);

INSERT INTO events_categories(
            category_id, event_id)
    VALUES (2, 2);
