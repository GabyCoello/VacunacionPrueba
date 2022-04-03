-- Database: NombreBaseDatos
--DROP DATABASE if exists "NombreBaseDatos";
/*CREATE DATABASE "NombreBaseDatos"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Ecuador.1252'
    LC_CTYPE = 'Spanish_Ecuador.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
*/	
	
drop table if exists  empleados cascade;
drop table if exists  usuarios cascade;

CREATE TABLE IF NOT EXISTS  empleados
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    identificacion character varying(10)  NOT NULL,
	direccion_domicilio character varying(250) ,
    apellidos character varying(100)  NOT NULL,
    correo_electronico character varying(100)  NOT NULL,
    fecha_nacimiento date,
    fecha_vacunacion date,
    nombres character varying(100)  NOT NULL,
    dosis integer default 0,
    telefono character varying(25) ,
    tipo_vacuna character varying(50) ,
    estado_vacunacion integer  default 0,
	
    CONSTRAINT empleados_pkey PRIMARY KEY (id),
    CONSTRAINT uk_identificacion_E UNIQUE (identificacion)
);
 
CREATE TABLE IF NOT EXISTS  usuarios
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    contrasena character varying(100)   NOT NULL,
    identificacion character varying(10)   NOT NULL,
    nombres character varying(100)   NOT NULL,
    rol character varying(50)   NOT NULL,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id),
    CONSTRAINT uk_identificacion_U UNIQUE (identificacion)
);

INSERT INTO  usuarios(
	 contrasena, identificacion, nombres, rol)	VALUES ('1714814306', '1714814306', 'Gabriela Coello', 'ADMINISTRADOR');
INSERT INTO  usuarios(
	 contrasena, identificacion, nombres, rol) 	VALUES ('1717708687', '1717708687', 'Edison Guachamin', 'ADMINISTRADOR');
	
INSERT INTO empleados(
	 identificacion, direccion_domicilio, nombres, apellidos, correo_electronico)
	 VALUES ('1714814306', 'Quito', 'Gabriela', 'Coello', 'gcoello@gmail.com');	
INSERT INTO empleados(
	 identificacion, direccion_domicilio, nombres, apellidos, correo_electronico)
	 VALUES ('1717708687', 'Quito', 'Edison', 'Guachamin', 'eguachamin@gmail.com');		 
	 	 