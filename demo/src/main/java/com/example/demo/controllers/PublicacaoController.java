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

import java.util.List;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacaoService publicacaoService;
    @Autowired
    private PublicacaoRepository publicacaoRepository;

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
        publi.setDescricao(publicacaoDTO.descricao());
        publi.setData_publi(publicacaoDTO.data_publi());
        publi.setIdUsuarioCriador(usuarioCriador); // Associa o usuário criador

        usuarioCriador.setPublicacao(publi);

        repository.save(publi);

        var uri = uriBuilder.path("/publicacao/{id_publi}").buildAndExpand(publi.getId_publi()).toUri();

        return ResponseEntity.created(uri).body(new DTOPublicacao(publi));
    }

    @GetMapping("{id}")
    public ResponseEntity<DTOPublicacao> buscarPublicacao(@PathVariable Long id) {
        Publicacao publicacao = repository.getReferenceById(id);

        return ResponseEntity.ok(new DTOPublicacao(publicacao.getId_publi(), publicacao.getRede_social(), publicacao.getDescricao(), publicacao.getData_publi(), publicacao.getIdUsuarioCriador()));
    }

    @GetMapping("/publicacoesUsuario")
    public ResponseEntity<List<Publicacao>> buscarPublicacoesUsuario(@RequestParam Long id_usuario) {
        var usuarioOptional = usuarioRepository.findById(id_usuario);

        if (usuarioOptional.isEmpty()) { return ResponseEntity.badRequest().build(); }

        var usuario = usuarioOptional.get();

        List<Publicacao> publicacaoList = repository.findByIdUsuarioCriador(usuario);

        return ResponseEntity.ok(publicacaoList);
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
