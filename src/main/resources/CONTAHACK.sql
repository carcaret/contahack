DROP TABLE crentialInfo IF EXISTS;
CREATE TABLE crentialInfo(uid VARCHAR(30), user_remote VARCHAR(255), pass_remote VARCHAR(255));

DROP TABLE customers IF EXISTS;
CREATE TABLE customers(uid VARCHAR(30), first_name VARCHAR(255), last_name VARCHAR(255));


DROP TABLE agrupaciones IF EXISTS;
CREATE TABLE agrupaciones(name VARCHAR(255), description VARCHAR(255));


DROP TABLE tags IF EXISTS;
CREATE TABLE tags(name VARCHAR(255), description VARCHAR(255));


DROP TABLE agrupaciones2tags IF EXISTS;
CREATE TABLE tags2agrupaciones(agrupacion VARCHAR(255), tags VARCHAR(255));



INSERT INTO customers(uid, first_name, last_name) VALUES ('cesinrm','Cesar','Rodriguez');


INSERT INTO crentialInfo(uid, user_remote, pass_remote) VALUES ('cesinrm','cesinrm@gmail.com','falcons666');

INSERT INTO agrupaciones(name, description) VALUES ('Gastos generales','Esto son los gastos generales');
INSERT INTO agrupaciones(name, description) VALUES ('Ingresos','Esto son los gastos pareja');

INSERT INTO tags(name, description) VALUES ('clientesIngresos','Esto son los Ingresos!!');
INSERT INTO tags(name, description) VALUES ('proveedoresAlquileres','Esto son los gastos de alquiler');
INSERT INTO tags(name, description) VALUES ('personalNominas','Esto son los gastos de nominas');
INSERT INTO tags(name, description) VALUES ('proveedoresAgua','Esto son los gastos de Agua');
INSERT INTO tags(name, description) VALUES ('pagoProveedor','Esto son los gastos de Agua');


INSERT INTO tags2agrupaciones(agrupacion, tags) VALUES ('Gastos generales','proveedoresAgua');
INSERT INTO tags2agrupaciones(agrupacion, tags) VALUES ('Gastos generales','personalNominas');
INSERT INTO tags2agrupaciones(agrupacion, tags) VALUES ('Gastos generales','pagoProveedor');
INSERT INTO tags2agrupaciones(agrupacion, tags) VALUES ('Gastos generales','proveedoresAlquileres');
INSERT INTO tags2agrupaciones(agrupacion, tags) VALUES ('Ingresos','clientesIngresos');





