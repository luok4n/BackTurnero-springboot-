CREATE TABLE CONSECUTIVOS(
    ID INT AUTO_INCREMENT,
    CATEGORIA VARCHAR(150) NOT NULL,
    CONSECUTIVO INT,
CONSTRAINT COSECUTIVOS_PK PRIMARY KEY (ID)
);

CREATE TABLE CATEGORIAS (
    ID INT AUTO_INCREMENT,
    NOMBRE VARCHAR(150) NOT NULL,
CONSTRAINT CATEGORIA_PK PRIMARY KEY (ID)
);

CREATE TABLE ASESORES (
    ID BIGINT AUTO_INCREMENT,
    NOMBRE VARCHAR(150) NOT NULL,
CONSTRAINT ASESOR_PK PRIMARY KEY (ID)
);

CREATE TABLE TURNOS (
    ID BIGINT AUTO_INCREMENT,
    NRO_TURNO INT NOT NULL,
    ASESOR_ID BIGINT,
    CATEGORIA_ID INT NOT NULL,
    ESTADO_ID CHAR NOT NULL,
    TIEMPO_SOLICITUD DATETIME,
    TIEMPO_ATENCION DATETIME,
    TIEMPO_FINALIZACION DATETIME,
CONSTRAINT TURNO_PK PRIMARY KEY (ID),
CONSTRAINT FK_TURNO_CATEGORIA FOREIGN KEY (CATEGORIA_ID) REFERENCES CATEGORIAS(ID),
CONSTRAINT FK_TURNO_ASESOR FOREIGN KEY (ASESOR_ID) REFERENCES ASESORES(ID)
    
);

