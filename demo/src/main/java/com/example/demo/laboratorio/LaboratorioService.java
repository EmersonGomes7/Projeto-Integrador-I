package com.example.demo.laboratorio;

import org.springframework.stereotype.Service;

@Service
public class LaboratorioService {

    public void atualizarInformacoes(Laboratorio laboratorio, DTOLaboratorio laboratorioAtualizar) {
        if(laboratorioAtualizar.nome_lab() != null){
            laboratorio.setNome_lab(laboratorioAtualizar.nome_lab());
        }
        if(laboratorioAtualizar.capacidade() > 0){
            laboratorio.setCapacidade(laboratorioAtualizar.capacidade());
        }
    }
}
