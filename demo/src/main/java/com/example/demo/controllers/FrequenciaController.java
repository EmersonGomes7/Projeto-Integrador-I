package com.example.demo.controllers;

import com.example.demo.frequencia.DTOFrequencia;
import com.example.demo.frequencia.Frequencia;
import com.example.demo.frequencia.FrequenciaRepository;
import com.example.demo.frequencia.FrequenciaService;
import com.example.demo.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/frequencia")
public class FrequenciaController {

    @Autowired
    private FrequenciaRepository repository;
    @Autowired
    private FrequenciaService frequenciaService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOFrequencia> criarFrequencia(@RequestBody DTOFrequencia frequenciaDTO, UriComponentsBuilder uriBuilder) {
        // Checagem da existencia de um usuário
        var professorOptional = usuarioRepository.findById(frequenciaDTO.id_usuario_prof());
        if (professorOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var professor = professorOptional.get();

        var frequencia = new Frequencia();
        frequencia.setData(frequenciaDTO.data());
        frequencia.setHora(frequenciaDTO.hora());
        frequencia.setFreq_alunos(frequenciaDTO.lab_frequencia());
        frequencia.setPresenca_alunos(frequenciaDTO.tipo_de_usua());
        frequencia.setIdUsuarioProf(professor);

        repository.save(frequencia);

        var uri = uriBuilder.path("/frequencia/{id_frequencia}").buildAndExpand(frequencia.getId_frequencia()).toUri();

        return ResponseEntity.created(uri).body(new DTOFrequencia(frequencia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOFrequencia> listarFrequencia(@PathVariable Long id) {
        var frequencia = repository.findById(id).orElse(null);

        if (frequencia == null){ return ResponseEntity.notFound().build(); }

        return ResponseEntity.ok(new DTOFrequencia(frequencia.getId_frequencia(), frequencia.getData(), frequencia.getHora(), frequencia.getFreq_alunos(), frequencia.getPresenca_alunos(), frequencia.getIdUsuarioProf()));
    }

    @GetMapping("/frequenciasProfessor")
    public ResponseEntity<List<Frequencia>> listarFrequenciaProfessor(@RequestParam Long idProfessor) {
        var professorOptional = usuarioRepository.findById(idProfessor);
        if (professorOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var professor = professorOptional.get();

        List<Frequencia> frequenciaList = repository.findByIdUsuarioProf(professor);

        return ResponseEntity.ok(frequenciaList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOFrequencia> atualizarFreq(@RequestBody DTOFrequencia frequenciaDTO) {
        var freq = repository.findById(frequenciaDTO.id()).orElse(null);
        frequenciaService.AtualizarFrequencia(freq, frequenciaDTO);

        if(freq == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new DTOFrequencia(freq));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DTOFrequencia> deletarFrequencia(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
