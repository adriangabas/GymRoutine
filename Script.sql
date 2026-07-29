INSERT INTO grupos_musculares (nombre, descripcion, imagen_url)
VALUES
('Pecho', 'Grupo muscular situado en la parte frontal superior del torso.', NULL),
('Espalda', 'Conjunto de músculos de la zona posterior del torso.', NULL),
('Hombros', 'Grupo muscular encargado de los movimientos del hombro.', NULL),
('Bíceps', 'Músculo situado en la parte anterior del brazo.', NULL),
('Tríceps', 'Músculo situado en la parte posterior del brazo.', NULL),
('Antebrazos', 'Grupo muscular del antebrazo responsable del agarre y la flexión de la muñeca.', NULL),
('Abdominales', 'Grupo muscular encargado de la estabilidad del tronco.', NULL),
('Lumbares', 'Músculos de la parte baja de la espalda.', NULL),
('Glúteos', 'Grupo muscular de la cadera y la pelvis.', NULL),
('Cuádriceps', 'Grupo muscular situado en la parte frontal del muslo.', NULL),
('Isquiotibiales', 'Grupo muscular situado en la parte posterior del muslo.', NULL),
('Gemelos', 'Músculos de la pantorrilla.', NULL);

SELECT * FROM grupos_musculares;

INSERT INTO ejercicios (
    nombre,
    descripcion,
    ejecucion,
    errores_frecuentes,
    material,
    musculo_principal_id,
    imagen_url,
    video_url
)
VALUES
(
    'Press banca',
    'Ejercicio compuesto para desarrollar el pecho.',
    'Túmbate en un banco, baja la barra hasta el pecho y empuja de forma controlada.',
    'Rebotar la barra sobre el pecho o levantar los glúteos.',
    'Barra y banco',
    1,
    NULL,
    NULL
),
(
    'Dominadas',
    'Ejercicio compuesto para la espalda.',
    'Cuelga de la barra y eleva el cuerpo hasta que la barbilla supere la barra.',
    'Balancear el cuerpo excesivamente.',
    'Barra de dominadas',
    2,
    NULL,
    NULL
),
(
    'Press militar',
    'Ejercicio para desarrollar los hombros.',
    'Empuja la barra desde los hombros hasta extender completamente los brazos.',
    'Arquear demasiado la espalda.',
    'Barra',
    3,
    NULL,
    NULL
),
(
    'Curl con barra',
    'Ejercicio de aislamiento para bíceps.',
    'Flexiona los codos manteniéndolos pegados al cuerpo.',
    'Mover los hombros para ayudar al levantamiento.',
    'Barra',
    4,
    NULL,
    NULL
),
(
    'Sentadilla',
    'Ejercicio básico para piernas.',
    'Desciende flexionando rodillas y caderas manteniendo la espalda recta.',
    'Levantar los talones o redondear la espalda.',
    'Barra',
    10,
    NULL,
    NULL
);

SELECT * FROM ejercicios;