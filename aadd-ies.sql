DROP DATABASE if EXISTS aadd;
CREATE DATABASE aadd;

/*
DROP TABLE IF EXISTS T_ALUMNO_MODULO_CICLO
DROP TABLE IF EXISTS T_CURSO;
DROP TABLE IF EXISTS T_MODULOS_CICLO;
DROP TABLE IF EXISTS T_CICLO;
DROP TABLE IF EXISTS T_MODULO;
DROP TABLE IF EXISTS T_ALUMNO;
DROP TABLE IF EXISTS T_TIPO_FORMACION;
DROP TABLE IF EXISTS T_FAMILIA_FORMATIVA;
DROP TABLE IF EXISTS T_NIVEL;

*/



# Crear la tabla T_ALUMNO
CREATE TABLE IF NOT EXISTS T_ALUMNO(
	id_alumno INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	nombre VARCHAR(255) NOT NULL,
	apellido1 VARCHAR(255) NOT NULL,
	apellido2 VARCHAR(255),
	referencia VARCHAR(10),
	email VARCHAR(255) NOT NULL,
	telefono INT NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

# Crear la tabla T_TIPO_FORMACION
CREATE TABLE IF NOT EXISTS T_TIPO_FORMACION(
	id_tipo_formacion INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	desc_tipo_formacion VARCHAR(255) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

# Crear la tabla T_FAMILIA_FORMATIVA
CREATE TABLE IF NOT EXISTS T_FAMILIA_FORMATIVA(
	id_familia INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	desc_familia VARCHAR(255) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

# Crear la tabla T_NIVEL
CREATE TABLE IF NOT EXISTS T_NIVEL(
	id_nivel INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	num_nivel INT NOT NULL,
	desc_nivel VARCHAR(255) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

# Crear la tabla T_MODULO
CREATE TABLE IF NOT EXISTS T_MODULO(
	id_modulo INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	desc_modulo VARCHAR(255) NOT NULL,
	referencia VARCHAR(5) NOT NULL,
	id_nivel INT NOT NULL,	
	CONSTRAINT FOREIGN KEY (id_nivel) REFERENCES T_NIVEL(id_nivel)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;


# Crear la tabla T_CICLO
CREATE TABLE IF NOT EXISTS T_CICLO(
	id_ciclo INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	desc_ciclo VARCHAR(255) NOT NULL,
	id_familia INT NOT NULL,
	id_tipo_formacion INT NOT NULL,
	CONSTRAINT FOREIGN KEY (id_familia) REFERENCES T_FAMILIA_FORMATIVA(id_familia),
	CONSTRAINT FOREIGN KEY (id_tipo_formacion) REFERENCES T_TIPO_FORMACION(id_tipo_formacion)	
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;


# Crear la tabla T_MODULOS_CICLO
CREATE TABLE IF NOT EXISTS T_MODULOS_CICLO(
	id_ciclo INT NOT NULL,
	id_modulo INT NOT NULL,
	CONSTRAINT pk PRIMARY KEY (id_ciclo, id_modulo),
	CONSTRAINT FOREIGN KEY (id_ciclo) REFERENCES T_CICLO(id_ciclo),
	CONSTRAINT FOREIGN KEY (id_modulo) REFERENCES T_MODULO(id_modulo)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

# Crear la tabla T_CURSO
CREATE TABLE IF NOT EXISTS T_CURSO(
	id_curso INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	desc_curso VARCHAR(255) NOT NULL,
	id_ciclo INT NOT NULL,
	id_nivel INT NOT NULL,	
	CONSTRAINT FOREIGN KEY (id_nivel) REFERENCES T_NIVEL(id_nivel),	
	CONSTRAINT FOREIGN KEY (id_ciclo) REFERENCES T_CICLO(id_ciclo)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

# Crear la tabla T_ALUMNO_MODULO_CICLO
CREATE TABLE IF NOT EXISTS T_ALUMNO_MODULO_CICLO(
	id_alumno INT NOT NULL,
	id_modulo INT NOT NULL,
	id_ciclo INT NOT NULL,
	nota INT NOT NULL DEFAULT 0,
	CONSTRAINT pk PRIMARY KEY (id_alumno, id_ciclo, id_modulo),
	CONSTRAINT FOREIGN KEY (id_alumno) REFERENCES T_ALUMNO(id_alumno),
	CONSTRAINT FOREIGN KEY (id_modulo) REFERENCES T_MODULO(id_modulo),	
	CONSTRAINT FOREIGN KEY (id_ciclo) REFERENCES T_CICLO(id_ciclo)	
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

