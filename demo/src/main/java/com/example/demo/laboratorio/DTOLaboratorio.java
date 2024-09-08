package com.example.demo.laboratorio;

public record DTOLaboratorio(
        Long id,
        String nome_lab,
        int capacidade
) {
    public DTOLaboratorio(Laboratorio laboratorio) {
        this(
                laboratorio.getId_lab(),
                laboratorio.getNome_lab(),
                laboratorio.getCapacidade()
        );
    }
}
