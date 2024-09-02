package com.example.demo.controllers;

import com.example.demo.publicacao.DTOPublicacao;
import com.example.demo.publicacao.Publicacao;
import com.example.demo.publicacao.PublicacaoRepository;
import com.example.demo.publicacao.PublicacaoService;
import com.example.demo.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacaoService publicacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOPublicacao> publicacaoCadastro(@RequestBody @Valid DTOPublicacao publicacaoDTO, UriComponentsBuilder uriBuilder) {
        // Checagem da existencia de um usuário
        var usuarioCriadorOptional = usuarioRepository.findById(publicacaoDTO.id_usuario_criador());
        if (usuarioCriadorOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var usuarioCriador = usuarioCriadorOptional.get();

        var publi = new Publicacao();
        publi.setRede_social(publicacaoDTO.rede_social());
        publi.setData_publi(publicacaoDTO.data_publi());
        publi.setId_usuario_criador(usuarioCriador); // Associa o usuário criador

        repository.save(publi);

        var uri = uriBuilder.path("/publicacao/{id_publi}").buildAndExpand(publi.getId_publi()).toUri();

        return ResponseEntity.created(uri).body(new DTOPublicacao(publi));
    }

    @GetMapping("{id}")
    public ResponseEntity<DTOPublicacao> buscarPublicacao(@PathVariable Long id) {
        Publicacao publicacao = repository.getReferenceById(id);

        return ResponseEntity.ok(new DTOPublicacao(publicacao.getId_publi(), publicacao.getRede_social(), publicacao.getData_publi(), publicacao.getId_usuario_criador()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOPublicacao> atualizarPubli(@RequestBody @Valid DTOPublicacao publicacaoAtualizar) {
        var publi = repository.getReferenceById(publicacaoAtualizar.id_publi());
        publicacaoService.atualizarPublicacao(publi, publicacaoAtualizar);

        return ResponseEntity.ok(new DTOPublicacao(publi));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<DTOPublicacao> deletarPubli(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
