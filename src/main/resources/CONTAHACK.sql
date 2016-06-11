DROP TABLE crentialInfo IF EXISTS;
CREATE TABLE crentialInfo(uid VARCHAR(30), user_remote VARCHAR(255), pass_remote VARCHAR(255));

DROP TABLE customers IF EXISTS;
CREATE TABLE customers(uid VARCHAR(30), first_name VARCHAR(255), last_name VARCHAR(255));


DROP TABLE agrupaciones IF EXISTS;
CREATE TABLE agrupaciones(name VARCHAR(255), description VARCHAR(255));


DROP TABLE tags IF EXISTS;
CREATE TABLE tags(name VARCHAR(255), description VARCHAR(255));


DROP TABLE agrupaciones2tags IF EXISTS;
CREATE TABLE agrupaciones2tags(agrupacion VARCHAR(255), tags VARCHAR(255));



INSERT INTO customers(uid, first_name, last_name) VALUES ('cesinrm','Cesar','Rodriguez');


INSERT INTO crentialInfo(uid, user_remote, pass_remote) VALUES ('cesinrm','cesinrm@gmail.com','falcons666');

INSERT INTO agrupaciones(name, description) VALUES ('Gastos generales','Esto son los gastos generales');
INSERT INTO agrupaciones(name, description) VALUES ('Gastos pareja','Esto son los gastos pareja');

INSERT INTO tags(name, description) VALUES ('Comida','Esto son los gastos de comida');
INSERT INTO tags(name, description) VALUES ('Luz','Esto son los gastos de luz');
INSERT INTO tags(name, description) VALUES ('Agua','Esto son los gastos de agua');


INSERT INTO agrupaciones2tags(agrupacion, tags) VALUES ('Gastos generales','Comida');
INSERT INTO agrupaciones2tags(agrupacion, tags) VALUES ('Gastos generales','Luz');
INSERT INTO agrupaciones2tags(agrupacion, tags) VALUES ('Gastos generales','Agua');





