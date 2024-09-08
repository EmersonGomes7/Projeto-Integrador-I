package com.example.demo.controllers;

import com.example.demo.laboratorio.DTOLaboratorio;
import com.example.demo.laboratorio.Laboratorio;
import com.example.demo.laboratorio.LaboratorioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOLaboratorio> laboratorioCadastro(@RequestBody @Valid DTOLaboratorio laboratorioDTO, UriComponentsBuilder uriBuilder){
        var laboratorio = new Laboratorio(laboratorioDTO);
        repository.save(laboratorio);

        var uri =  uriBuilder.path("/laboratorio/{id_lab}").buildAndExpand(laboratorio.getId_lab()).toUri();

        return ResponseEntity.created(uri).body(new DTOLaboratorio(laboratorio));
    }

    @GetMapping
    public ResponseEntity<List<DTOLaboratorio>> listarLaboratorios(){
        var laboratorios = repository.findAll().stream().map(DTOLaboratorio::new).toList();

        return ResponseEntity.ok(laboratorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOLaboratorio> buscarLaboratorio(@PathVariable Long id){
        var laboratorio = repository.getReferenceById(id);

        return ResponseEntity.ok(new DTOLaboratorio(laboratorio));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOLaboratorio> atualizarLab(@RequestBody DTOLaboratorio laboratorioAtualizar){
        var lab = repository.getReferenceById(laboratorioAtualizar.id());
        lab.atualizarInformacoes(laboratorioAtualizar);

        return ResponseEntity.ok(new DTOLaboratorio(lab));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DTOLaboratorio> deletarLaboratorio(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
