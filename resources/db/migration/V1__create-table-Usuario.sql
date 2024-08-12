CREATE TABLE Usuario(
    id_usuario UUID DEFAULT RAMDOM_UUID() PRIMARY KEY,
    nome_usuario VARCHAR(50) NOT NULL,
    tipo_usuario VARCHAR(50) NOT NULL
);