CREATE TABLE users
(
  id serial NOT NULL,
  name character varying(50) NOT NULL,
  surname character varying(50) NOT NULL,
  password character varying(50) NOT NULL,
  phone character varying(50) NOT NULL,
  email character varying(50) NOT NULL,
  username character varying(50) NOT NULL,
  CONSTRAINT useridpkey PRIMARY KEY (id ),
  CONSTRAINT users_email_key UNIQUE (email ),
  CONSTRAINT users_username_key UNIQUE (username )
);


CREATE TABLE property
(
  id serial NOT NULL,
  neighbourhood character varying(50) NOT NULL,
  coveredSurface integer NOT NULL,
  uncoveredSurface integer NOT NULL,
  rooms integer NOT NULL,
  description character varying(512),
  age integer NOT NULL,
  street character varying(50) NOT NULL,
  numbering integer NOT NULL,
  floor integer default NULL,
  apartment character varying(5) default NULL,
  price integer NOT NULL,
  CONSTRAINT idproperpkey PRIMARY KEY (id )
);

CREATE TABLE service
(
  id serial NOT NULL,
  serviceName character varying(50) NOT NULL,
  CONSTRAINT idservicepkey PRIMARY KEY (id ),
  CONSTRAINT service_servicename_key UNIQUE (serviceName )
);

CREATE TABLE service_provided
(
  id serial NOT NULL,
  serviceId integer NOT NULL,
  propertyId integer NOT NULL,
  CONSTRAINT idserviceppkey PRIMARY KEY (id ),
  CONSTRAINT fk_proper2 FOREIGN KEY (propertyId)
      REFERENCES property (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT fk_service FOREIGN KEY (serviceId)
      REFERENCES service (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE operation
(
	id serial NOT NULL,
	operationName character varying(50) NOT NULL,
	CONSTRAINT idoperationpkey PRIMARY KEY (id ),
  CONSTRAINT operation_operationname_key UNIQUE (operationName )
);

CREATE TABLE property_operation
(
  id serial NOT NULL,
  operationId integer NOT NULL,
  propertyId integer NOT NULL,
  userId integer NOT NULL,
  status character varying(50)  NOT NULL,
  CONSTRAINT idpropertyppkey PRIMARY KEY (id ),
  CONSTRAINT fk_proper2 FOREIGN KEY (propertyId)
      REFERENCES property (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT fk_operation FOREIGN KEY (operationId)
      REFERENCES operation (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT fk_user FOREIGN KEY (userId)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE comments
(
	id serial NOT NULL,
	propertyId integer NOT NULL,
	comment character varying(300),
	CONSTRAINT pkey PRIMARY KEY(id),
	CONSTRAINT fk_proper2 FOREIGN KEY (propertyId)
      REFERENCES property (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE photos (
	id serial NOT NULL,
	propertyId integer NOT NULL,
	photo bytea NOT NULL,
	CONSTRAINT pkey2 PRIMARY KEY(id),
	CONSTRAINT fk_proper2 FOREIGN KEY (propertyId)
      REFERENCES property (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
);

ALTER TABLE users 
  OWNER TO paw;
ALTER TABLE property 
  OWNER TO paw;
ALTER TABLE service 
  OWNER TO paw;
ALTER TABLE service_provided 
  OWNER TO paw;
ALTER TABLE operation 
  OWNER TO paw;
ALTER TABLE property_operation 
  OWNER TO paw;
ALTER TABLE comments
  OWNER TO paw;
ALTER TABLE photos
  OWNER TO paw;
