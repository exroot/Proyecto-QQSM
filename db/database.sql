CREATE DATABASE juego;

CREATE TABLE usuarios (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    cedula INT UNSIGNED NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    puntaje DECIMAL,
    PRIMARY KEY (id)
);

CREATE TABLE categorias (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    categoria VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE dificultades (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    dificultad VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE preguntas (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    pregunta VARCHAR(255) NOT NULL,
    categoria_id INT UNSIGNED NOT NULL,
    dificultad_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    FOREIGN KEY (dificultad_id) REFERENCES dificultades(id)
);

CREATE TABLE respuestas (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    respuesta VARCHAR(255) NOT NULL,
    esCorrecta BOOLEAN,
    pregunta_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pregunta_id) REFERENCES preguntas(id)
);

CREATE TABLE partidas (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    usuario_id INT UNSIGNED NOT NULL,
    ronda INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- *** Join pregunta + respuesta correcta ***

-- SELECT pregunta, respuesta from preguntas
-- INNER JOIN respuestas
-- ON respuestas.pregunta_id = preguntas.id
-- WHERE respuestas.esCorrecta;