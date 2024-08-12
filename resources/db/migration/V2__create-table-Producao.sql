CREATE TABLE Producao(
    id_producao UUID DEFAULT RAMDOM_UUID() PRIMARY KEY,
    tipo_conteudo VARCHAR(50) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    data_publicacao TIMESTAMP NOT NULL,
    id_usuario_criador UUID,
    FOREIGN KEY (id_usuario_criador) REFERENCES Usuario(id_usuario) ON DELETE CASCADE
);