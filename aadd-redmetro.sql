DROP TABLE IF EXISTS T_LINEA_ESTACION;
	DROP TABLE IF EXISTS T_ACCESO;
	DROP TABLE IF EXISTS T_TREN;
	DROP TABLE IF EXISTS T_LINEA;
	DROP TABLE IF EXISTS T_COCHERA;
	DROP TABLE IF EXISTS T_COLOR;
	DROP TABLE IF EXISTS T_ESTACION;
	/* */
	# Crear la tabla T_COCHERA
	CREATE TABLE IF NOT EXISTS T_COCHERA(
		cod_cochera INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		nombre VARCHAR(255) NOT NULL,
		direccion VARCHAR(255) NOT NULL,
		deposito INT -- Valores 1  y 0
	)
	ENGINE=InnoDB
	DEFAULT CHARSET=utf8mb4
	COLLATE=utf8mb4_general_ci;
	
	# Crear la tabla T_COLOR
	CREATE TABLE IF NOT EXISTS T_COLOR(
		cod_color INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		nombre VARCHAR(100) NOT NULL,
		cod_hexadecimal VARCHAR(7) NOT NULL
	)
	ENGINE=InnoDB
	DEFAULT CHARSET=utf8mb4
	COLLATE=utf8mb4_general_ci;
	
	# Crear la tabla T_ESTACION
	CREATE TABLE IF NOT EXISTS T_ESTACION(
		cod_estacion INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		nombre VARCHAR(100) NOT NULL,
		direccion VARCHAR(255) NOT NULL
	)
	ENGINE=InnoDB
	DEFAULT CHARSET=utf8mb4
	COLLATE=utf8mb4_general_ci;
	
	
	# Crear la tabla T_ACCESO
	CREATE TABLE IF NOT EXISTS T_ACCESO(
		cod_acceso INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		nombre VARCHAR(100) NOT NULL,
		acceso_discapacidad INT NOT NULL, -- 1 y 0
		cod_estacion INT NOT NULL,	
		CONSTRAINT FOREIGN KEY (cod_estacion) REFERENCES T_ESTACION(cod_estacion)
	)
	ENGINE=InnoDB
	DEFAULT CHARSET=utf8mb4
	COLLATE=utf8mb4_general_ci;
	
	# Crear la tabla T_LINEA
	CREATE TABLE IF NOT EXISTS T_LINEA(
		cod_linea INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		nombre_corto VARCHAR(50) NOT NULL,
		nombre_largo VARCHAR(100) NOT NULL,
		-- fecha_inauguracion DATE NOT NULL,
		cod_color INT NOT NULL,	
		kilometros FLOAT(5,2) NOT NULL,	
		CONSTRAINT FOREIGN KEY (cod_color) REFERENCES T_COLOR(cod_color)
	)
	ENGINE=InnoDB
	DEFAULT CHARSET=utf8mb4
	COLLATE=utf8mb4_general_ci;
	
	
	# Crear la tabla T_TREN
	CREATE TABLE IF NOT EXISTS T_TREN(
		cod_tren INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
		modelo VARCHAR(100) NOT NULL,
		empresa_constructora VARCHAR(255) NOT NULL,
		fecha_incorporacion DATE ,
		cod_cochera INT NOT NULL,	
		cod_linea INT NOT NULL,
		CONSTRAINT FOREIGN KEY (cod_cochera) REFERENCES T_COCHERA(cod_cochera),	
		CONSTRAINT FOREIGN KEY (cod_linea) REFERENCES T_LINEA(cod_linea)	
	)
	ENGINE=InnoDB
	DEFAULT CHARSET=utf8mb4
	COLLATE=utf8mb4_general_ci;
	
	
	
	# Crear la tabla T_LINEA_ESTACION
	CREATE TABLE IF NOT EXISTS T_LINEA_ESTACION(
		cod_linea INT NOT NULL,	
		cod_estacion INT NOT NULL,	
		orden INT NOT NULL,	
		CONSTRAINT PRIMARY KEY (cod_linea, cod_estacion),
		CONSTRAINT FOREIGN KEY (cod_linea) REFERENCES T_LINEA(cod_linea),	
		CONSTRAINT FOREIGN KEY (cod_estacion) REFERENCES T_ESTACION(cod_estacion)	
	)
	ENGINE=InnoDB
	DEFAULT CHARSET=utf8mb4
	COLLATE=utf8mb4_general_ci;
	
	/* Carga de información */
	-- Tabla T_COLOR --
	INSERT INTO T_COLOR VALUES (1, 'Azul', '#438dc0');
	INSERT INTO T_COLOR VALUES (2, 'Rojo', '#fc3738');
	INSERT INTO T_COLOR VALUES (3, 'Amarillo', '#fec128');
	INSERT INTO T_COLOR VALUES (4, 'Marrón', '#741013');
	INSERT INTO T_COLOR VALUES (5, 'Verde', '#83b41c');
	INSERT INTO T_COLOR VALUES (6, 'Gris', '#939493');
	INSERT INTO T_COLOR VALUES (7, 'Naranja', '#fd9924');
	INSERT INTO T_COLOR VALUES (8, 'Rosa', '#f5418e');
	INSERT INTO T_COLOR VALUES (9, 'Morado', '#8a026b');
	INSERT INTO T_COLOR VALUES (10, 'Azul marino', '#09117e');
	INSERT INTO T_COLOR VALUES (11, 'Verde oscuro', '#065a16');
	INSERT INTO T_COLOR VALUES (12, 'Verde oliva', '#7f7f01');
	INSERT INTO T_COLOR VALUES (13, 'Blanco ramal', '#ffffff');
	
	-- Tabla T_ESTACION --
	-- Tabla T_TREN --
	-- Tabla T_ACCESO --
	-- Tabla T_LINEA --
	INSERT INTO T_LINEA (cod_linea,nombre_corto, nombre_largo,cod_color,kilometros) values (5, 'Línea 4', 'Arguelles - Pinar de Chamartín', 4, 7.5);
	INSERT INTO T_LINEA (cod_linea,nombre_corto, nombre_largo,cod_color,kilometros) values (6, 'Línea 5', 'Alameda de Osuna - Casa de Campo', 5, 6.5);
	INSERT INTO T_LINEA (cod_linea,nombre_corto, nombre_largo,cod_color,kilometros) values (9, 'Línea 8', 'Nuevos Ministerios - Aeropuerto', 8, 7.45);
	INSERT INTO T_LINEA (cod_linea,nombre_corto, nombre_largo,cod_color,kilometros) values (10, 'Línea 9', 'Paco de Lucía -  Arganda del Rey', 9, 10.75);
	INSERT INTO T_LINEA (cod_linea,nombre_corto, nombre_largo,cod_color,kilometros) values (11, 'Línea 10', 'Hospital Infanta Sofía - Puerta del Sur', 10, 12.75);
	INSERT INTO T_LINEA (cod_linea,nombre_corto, nombre_largo,cod_color,kilometros) values (12, 'Línea 11', 'Plaza Elíptica -  La Fortuna', 11, 3.75);
	INSERT INTO T_LINEA (cod_linea,nombre_corto, nombre_largo,cod_color,kilometros) values (13, 'Línea 12', 'Metrour', 12, 10.75);
	
	
	-- Tabla T_LINEA_ESTACION --
	
	-- Tabla T_COCHERA --
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (1, 1, 'PUERTA DE ARGANDA', 'CARRETERA M-203 KM 2,300 (CAMINO DE VALLECAS A MEJORADA)');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (2, 1, 'UNIVERSIDAD REY JUAN CARLOS', 'AVENIDA ALCALDE DE MÓSTOLES Nº 35');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (3, 1, 'EL BERCIAL', 'PRÓXIMO A CARRETERA DE TURQUÍA (BARRIO DE EL BERCIAL)');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (4, 1, 'ARGANZUELA - PLANETARIO', 'C/ BRONCE Nº 1');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (5, 1, 'CIUDAD UNIVERSITARIA', 'PLAZA RAMÓN Y CAJAL');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (6, 1, 'MIGUEL HERNANDEZ', 'AVENIDA DE LA ALBUFERA ESQ. CALLE RAFAEL ALBERTI');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (7, 1, 'ARGÜELLES', 'C/ PRINCESA Nº 56');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (8, 1, 'ALMENDRALES', 'AVENIDA DE CÓRDOBA, 21');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (9, 1, 'MONCLOA', 'C/ PRINCESA, 96');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (10, 1, 'NUEVOS MINISTERIOS', 'PASEO DE LA CASTELLANA Nº 67');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (11, 0, 'CUATRO CAMINOS', 'VIRGEN DE NIEVA S/N');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (12, 0, 'VENTAS', 'ALMERÍA 6');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (13, 0, 'PLAZA DE CASTILLA', 'SAN BENITO S/N');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (14, 0, 'CANILLEJAS', 'NECTAR S/N');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (15, 0, 'ALUCHE', 'AVDA. DE LOS POBLADOS Nº 11');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (16, 0, 'FUENCARRAL', 'AVENIDA CAMPO DE CALATRAVA Nº 86');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (17, 0, 'SACEDAL', 'VENTISQUERO DE LA CONDESA S/N');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (18, 0, 'LAGUNA', 'GOTAREDURA Nº 3');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (19, 0, 'HORTALEZA', 'C/ MANUEL AZAÑA');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (20, 0, 'CUATRO VIENTOS', 'CRTA. DE CANALEJAS S/N');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (21, 0, 'LORANCA', 'AVDA. DE LA ALEGRÍA (FUENLABRADA)');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (22, 0, 'VALDECARROS', 'AVENIDA CERRO MILANO 0103 ENSANCHE DE VALLECAS, 73 ');
	INSERT INTO T_COCHERA (cod_cochera, deposito ,nombre, direccion) VALUES (23, 0, 'VILLAVERDE', 'AVENIDA REAL DE PINTO');