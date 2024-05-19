create database gestion_humana_bd;
use gestion_humana_bd;
create table Funcionarios(
	id int auto_increment not null primary key,
    id_tipo_documento INT not null,
    documento varchar (30) not null,
    nombres varchar (50) not null,
    apellidos varchar (50) not null,
    id_estado_civil int not null,
    id_genero int not null,
    direccion varchar (50) not null,
    telefono varchar (10) not null,
    fecha_nacimiento date not null,
     FOREIGN KEY (id_tipo_documento) REFERENCES Tipos_Doc(id_tipo_documento),
     FOREIGN KEY (id_estado_civil) REFERENCES estado_civil(id_estado_civil),
     FOREIGN KEY (id_genero) REFERENCES generos(id_genero)
);
create table Tipos_Doc(
	id_tipo_documento INT PRIMARY KEY,
    tipo_documento VARCHAR(30) NOT NULL
    
);
create table estado_civil(
id_estado_civil INT PRIMARY KEY,
estado_civil VARCHAR(30) NOT NULL
);
CREATE TABLE generos (
    id_genero INT PRIMARY KEY,
    genero VARCHAR(50) NOT NULL
);
create table Formacion_funcionarios(
 id_formacion INT PRIMARY KEY AUTO_INCREMENT,
 id_funcionario int not null,
 titulo VARCHAR(100) NOT NULL,
 institucion VARCHAR(100) NOT NULL,
 anho_obtencion varchar (10),
 FOREIGN KEY ( id_formacion ) REFERENCES Funcionarios(id)
);
create table familiares(
id_familiar INT PRIMARY KEY AUTO_INCREMENT,
id_funcionario int not null,
nombres varchar (50) not null,
apellidos varchar (50) not null,
telefono varchar (50) not null,
parentesco varchar (50) not null,
FOREIGN KEY (id_funcionario) REFERENCES Funcionarios(id)
);
INSERT INTO Tipos_Doc  VALUES
(1,'Cédula de Ciudadanía'),
(2,'Tarjeta de Identidad'),
(3,'Pasaporte'),
(4,'Cedula de extranjeria'),
(5,'Permiso de permanencia');
select * FROM Tipos_Doc;
INSERT INTO estado_civil (id_estado_civil, estado_civil) VALUES
(1, 'Soltero'),
(2, 'Casado'),
(3, 'Divorciado'),
(4, 'Viudo');
INSERT INTO generos (id_genero, genero) VALUES
(1, 'Masculino'),
(2, 'Femenino'),
(3, 'Prefiero no decirlo');
INSERT INTO Funcionarios (id_tipo_documento, documento, nombres, apellidos, id_estado_civil, id_genero, direccion, telefono, fecha_nacimiento) VALUES
(1, '1234567890', 'Juan', 'Pérez', 2, 1, 'Calle 123', '5551234', '1980-01-15'),
(2, '9876543210', 'María', 'Gómez', 1, 2, 'Avenida 456', '5555678', '1990-05-25'),
(3, '1122334455', 'Carlos', 'Rodríguez', 3, 1, 'Carrera 789', '5558765', '1975-09-10');
INSERT INTO Formacion_funcionarios (id_funcionario, titulo, institucion, anho_obtencion) VALUES
(1, 'Licenciatura en Administración', 'Universidad Nacional', '2003'),
(2, 'Maestría en Economía', 'Universidad de los Andes', '2015'),
(3, 'Doctorado en Derecho', 'Universidad Complutense', '2010');
INSERT INTO Familiares (id_funcionario, nombres, apellidos, telefono, parentesco) VALUES
(1, 'Ana', 'Pérez', '5551235', 'Hija'),
(2, 'Pedro', 'Gómez', '5555679', 'Padre'),
(3, 'Laura', 'Rodríguez', '5558766', 'Esposa');

