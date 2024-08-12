CREATE TABLE Publicacao(
    id_publi UUID DEFAULT RAMDOM_UUID() PRIMARY KEY,
    rede_social VARCHAR(50) NOT NULL,
    data_publi TIMESTAMP NOT NULL,
    id_usuario_criador UUID,
    FOREIGN KEY (id_usuario_criador) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);