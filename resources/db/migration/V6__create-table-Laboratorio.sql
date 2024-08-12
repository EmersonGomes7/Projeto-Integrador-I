CREATE TABLE Laboratorio(
    id_lab UUID DEFAULT RAMDOM_UUID() PRIMARY KEY,
    nome_lab VARCHAR(50) NOT NULL,
    capacidade INT NOT NULL,
    lab_frequencia VARCHAR(3) NOT NULL,
    id_reservado UUID NOT NULL,
    FOREIGN KEY (id_reservado) REFERENCES Usuario_reserva_Lab(id_reserva) ON DELETE CASCADE

);