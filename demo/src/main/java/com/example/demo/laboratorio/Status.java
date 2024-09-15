package com.example.demo.laboratorio;

import com.fasterxml.jackson.annotation.JsonFormat;
// TODO: APARENTEMENTE ELE NÃO ESTÁ MUDANDO DE RESERVADO PARA NÃO RESERVADO
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Status {
    RESERVADO,
    NAO_RESERVADO
}
