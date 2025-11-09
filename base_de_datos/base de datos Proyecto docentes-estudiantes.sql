DROP DATABASE IF EXISTS colegio;
CREATE DATABASE colegio CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE colegio;

-- 1. Periodos académicos
CREATE TABLE periodos_academicos (
    periodo_academico_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_periodo VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);

INSERT INTO periodos_academicos (nombre_periodo, fecha_inicio, fecha_fin) VALUES
('2024-1', '2024-01-15', '2024-06-15'),
('2024-2', '2024-07-01', '2024-12-01');

-- 2. Docentes
CREATE TABLE docentes (
    docente_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_docente VARCHAR(150) NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    tipo_identificacion VARCHAR(50) NOT NULL,
    genero VARCHAR(20),
    correo VARCHAR(150),
    titulo_estudios VARCHAR(200),
    idiomas VARCHAR(200),
    certificaciones VARCHAR(300)
);

INSERT INTO docentes (nombre_docente, identificacion, tipo_identificacion, genero, correo, titulo_estudios, idiomas, certificaciones) VALUES
('María Fernanda López', '10223344', 'CC', 'F', 'mflopez@uni.edu', 'Maestría en Educación', 'Español, Inglés', 'Docencia Universitaria'),
('Carlos Mejía', '99887766', 'CC', 'M', 'carlosmejia@uni.edu', 'Licenciado en Matemáticas', 'Español', 'Didáctica de las Matemáticas');

-- 3. Cursos
CREATE TABLE cursos (
    curso_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_curso VARCHAR(150) NOT NULL,
    periodo_academico_id INT NOT NULL,
    docente_id INT NOT NULL,
    descripcion_curso TEXT,
    FOREIGN KEY (periodo_academico_id) REFERENCES periodos_academicos(periodo_academico_id),
    FOREIGN KEY (docente_id) REFERENCES docentes(docente_id)
);

INSERT INTO cursos (nombre_curso, periodo_academico_id, docente_id, descripcion_curso) VALUES
('Matemáticas I', 1, 2, 'Curso introductorio de matemáticas básicas.'),
('Pedagogía General', 1, 1, 'Fundamentos de la pedagogía moderna.');

-- 4. Relación docentes-cursos
CREATE TABLE docentes_cursos (
    docente_id INT NOT NULL,
    curso_id INT NOT NULL,
    PRIMARY KEY (docente_id, curso_id),
    FOREIGN KEY (docente_id) REFERENCES docentes(docente_id),
    FOREIGN KEY (curso_id) REFERENCES cursos(curso_id)
);

INSERT INTO docentes_cursos (docente_id, curso_id) VALUES
(2, 1),
(1, 2);

-- 5. Estudiantes
CREATE TABLE estudiantes (
    estudiante_id INT PRIMARY KEY AUTO_INCREMENT,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    correo_institucional VARCHAR(150),
    correo_personal VARCHAR(150),
    telefono VARCHAR(20),
    se_exonera BOOLEAN DEFAULT FALSE,
    comentarios TEXT,
    tipo_documento VARCHAR(50),
    genero VARCHAR(20)
);

INSERT INTO estudiantes (identificacion, nombre, correo_institucional, correo_personal, telefono, se_exonera, comentarios, tipo_documento, genero) VALUES
('123456789', 'Laura Rivera', 'laura.rivera@uni.edu', 'lr@gmail.com', '3104567890', FALSE, 'Sin novedades', 'CC', 'F'),
('987654321', 'Juan Morales', 'juan.morales@uni.edu', 'jm@gmail.com', '3129876543', FALSE, 'Buen desempeño', 'CC', 'M');

-- 6. Clases
CREATE TABLE clases (
    clase_id INT PRIMARY KEY AUTO_INCREMENT,
    curso_id INT NOT NULL,
    numero_clase INT NOT NULL,
    fecha_clase DATE NOT NULL,
    tema_clase VARCHAR(200),
    descripcion_clase TEXT,
    comentarios_clase TEXT,
    FOREIGN KEY (curso_id) REFERENCES cursos(curso_id)
);

INSERT INTO clases (curso_id, numero_clase, fecha_clase, tema_clase, descripcion_clase, comentarios_clase) VALUES
(1, 1, '2024-02-01', 'Números reales', 'Introducción a los números reales', 'Clase sin novedades'),
(1, 2, '2024-02-05', 'Álgebra básica', 'Conceptos iniciales de álgebra', 'Participación activa'),
(2, 1, '2024-02-03', 'Historia de la pedagogía', 'Conceptos históricos', 'Asistencia completa');

-- 7. Asistencias
CREATE TABLE asistencias (
    asistencia_id INT PRIMARY KEY AUTO_INCREMENT,
    estudiante_id INT NOT NULL,
    curso_id INT NOT NULL,
    fecha_clase DATE NOT NULL,
    estado_asistencia VARCHAR(50) NOT NULL,
    novedades TEXT,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(estudiante_id),
    FOREIGN KEY (curso_id) REFERENCES cursos(curso_id)
);

INSERT INTO asistencias (estudiante_id, curso_id, fecha_clase, estado_asistencia, novedades) VALUES
(1, 1, '2024-02-01', 'Asistió', 'no'),
(2, 1, '2024-02-01', 'Asistió', 'no'),
(1, 1, '2024-02-05', 'Faltó', 'Justificó'),
(2, 2, '2024-02-03', 'Asistió', 'ninguna');

-- 8. Cortes de evaluación
CREATE TABLE cortes_evaluacion (
    corte_evaluacion_id INT PRIMARY KEY AUTO_INCREMENT,
    curso_id INT NOT NULL,
    periodo_academico_id INT NOT NULL,
    nombre_corte VARCHAR(100) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    comentarios_corte TEXT,
    FOREIGN KEY (curso_id) REFERENCES cursos(curso_id),
    FOREIGN KEY (periodo_academico_id) REFERENCES periodos_academicos(periodo_academico_id)
);

INSERT INTO cortes_evaluacion (curso_id, periodo_academico_id, nombre_corte, porcentaje, comentarios_corte) VALUES
(1, 1, 'Primer Corte', 30.00, 'Incluye examen y tarea'),
(1, 1, 'Segundo Corte', 30.00, 'Incluye proyecto'),
(1, 1, 'Final', 40.00, 'Examen final y/o acumulado'),
(2, 1, 'Único Corte', 100.00, 'Evaluación teórica');

-- 9. Componentes de evaluación
CREATE TABLE componentes_evaluacion (
    componente_evaluacion_id INT PRIMARY KEY AUTO_INCREMENT,
    corte_evaluacion_id INT NOT NULL,
    nombre_componente VARCHAR(150) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (corte_evaluacion_id) REFERENCES cortes_evaluacion(corte_evaluacion_id)
);

INSERT INTO componentes_evaluacion (corte_evaluacion_id, nombre_componente, porcentaje) VALUES
(1, 'Examen Parcial', 70.00),
(1, 'Tarea 1', 30.00),
(2, 'Proyecto', 100.00),
(3, 'Examen Final', 100.00),
(4, 'Ensayo Crítico', 100.00);

-- 10. Calificaciones
CREATE TABLE calificaciones (
    calificacion_id INT PRIMARY KEY AUTO_INCREMENT,
    estudiante_id INT NOT NULL,
    componente_evaluacion_id INT NOT NULL,
    nota DECIMAL(4,2) NOT NULL,
    comentarios_calificacion TEXT,
    UNIQUE (estudiante_id, componente_evaluacion_id),
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(estudiante_id),
    FOREIGN KEY (componente_evaluacion_id) REFERENCES componentes_evaluacion(componente_evaluacion_id)
);

INSERT INTO calificaciones (estudiante_id, componente_evaluacion_id, nota, comentarios_calificacion) VALUES
(1, 1, 4.50, 'Buen examen'),
(2, 1, 3.80, 'Debe mejorar'),
(1, 2, 5.00, 'Excelente trabajo'),
(2, 3, 4.00, 'Cumple con lo esperado'),
(1, 4, 4.80, 'Muy buen análisis');
