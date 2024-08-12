CREATE TABLE Usuario_reserva_Lab(
    id_reserva UUID DEFAULT RAMDOM_UUID() PRIMARY KEY,
    data_reserva TIMESTAMP NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    hora_inicio TIMESTAMP NOT NULL,
    hora_fim TIMESTAMP NOT NULL,
    status_reserva VARCHAR(50) NOT NULL,
    lab_frequencia VARCHAR(3) NOT NULL,
    id_lab_reservado UUID NOT NULL,
    motivo_reserva VARCHAR(50) NOT NULL,
    id_solicitante UUID NOT NULL,
    FOREIGN KEY (id_solicitante) REFERENCES Usuario(id_usuario)
);