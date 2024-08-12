CREATE TABLE Frequencia(
    id_frequencia UUID DEFAULT RAMDOM_UUID() PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    hora TIMESTAMP NOT NULL,
    lab_frequencia VARCHAR(3) NOT NULL,
    id_usuario_prof UUID NOT NULL,
    tipo_de_usua VARCHAR(25) NOT NULL,
    FOREIGN KEY (id_usuario_prof) REFERENCES Usuario(id_usuario)
);