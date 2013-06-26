INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Esteban', 'Pintos','a','4-522-3465','epintos@alu.itba.edu.ar','epintos');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Matias', 'De Santi','123','4-522-4314','mdesanti90@gmail.com','mdesanti');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Federico', 'Di Nucci','a','4-522-4266','fdinucci@alu.itba.edu.ar','fdinucci');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Pepe', 'Garcia','a','4-234-4266','pepe@garcia.com','pepe');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Carlos', 'Perez','a','4-262-1295','carlos@perez.com','carlos');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Jorge', 'Weiz','a','4-678-3956','jorge@weiz.com','chorch');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Maria', 'Paz','a','4-486-0954','maria@paz.com','meri');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username) values('Victoria', 'Lima','a','4-495-2045','victoria@lima.com','vicky');

INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,price) values('Villa Urquiza', 324, 125,4, 'Linda casa con un buen fondo',5,'La Pampa','5671', 250000);
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,price) values('Colegiales', 256, 60 ,4, 'Excelente casa estilo colonial',3,'Delgado','966', 340000);
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,price) values('Belgrano', 800, 90 ,7, 'Casa grande, buena ubicacion',5,'Juramento','966', 900000);
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,price) values('Palermo', 540, 45 ,6, 'Moderna casa, gran parque',1,'Honduras','234', 520900);
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,price) values('Recoleta', 235, 27 ,4, 'Excelente casa, buena ubicacion',2,'Guido','12', 600500);
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,price) values('San Nicolas', 190, 20 ,2, 'Linda asa con lindo jardin',1,'Bartolome Mitre','1500', 200000);
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,price) values('Belgrano', 560, 45 ,4, 'Excelente casa antigua',12,'Ciudad de la Paz','390', 700000);

INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,floor,price,apartment) values('Belgrano', 80, 60 ,4, 'Departamento 4 ambientes, buenas condiciones',3,'Juramento','12',4, 300000, 'C');
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,floor,price,apartment) values('Palermo', 50, 60 ,4, 'Departamento 3 ambientes, moderno',1,'Thames','234',2, 150000,'D');
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,floor,price,apartment) values('Barracas', 70, 60 ,4, 'Departamento 4 ambientes, buena ubicacion',5,'Renacimiento','12',5, 200000,'B');
INSERT INTO PROPERTY(neighbourhood,coveredSurface ,uncoveredSurface ,rooms ,description ,age,street ,numbering ,floor,price,apartment) values('Palermo', 90, 60 ,4, 'Departamento 4 ambientes, excelente departamento',4,'Uriarte','485',9, 500000, 'E');

INSERT INTO SERVICE(serviceName) values('quincho');
INSERT INTO SERVICE(serviceName) values('cable');
INSERT INTO SERVICE(serviceName) values('telefono');
INSERT INTO SERVICE(serviceName) values('pileta');
INSERT INTO SERVICE(serviceName) values('salon');
INSERT INTO SERVICE(serviceName) values('cancha de paddle');

INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,1);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,2);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,3);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,4);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,5);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,6);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,7);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,8);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,9);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,10);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (3,11);

INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,1);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,2);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,3);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,4);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,5);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,6);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,7);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,8);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,9);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,10);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (2,11);

INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (4,1);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (4,2);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (4,3);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (4,4);

INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (1,1);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (1,4);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (1,5);

INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (6,3);

INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (5,7);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (5,9);
INSERT INTO SERVICE_PROVIDED(serviceId,propertyId) values (5,10);

INSERT INTO OPERATION(operationName) values('sale');
INSERT INTO OPERATION(operationName) values('rent');

INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(1,1,1,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(2,2,2,'finished');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(2,3,2,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(1,4,2,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(1,5,1,'finished');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(1,6,3,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(1,7,4,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(2,8,4,'finished');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(1,8,4,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(2,9,5,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(2,10,6,'available');
INSERT INTO PROPERTY_OPERATION(operationId,propertyId,userId,status) values(1,11,7,'available');
