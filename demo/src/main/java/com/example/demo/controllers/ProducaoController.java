package com.example.demo.controllers;

import com.example.demo.producao.DTOProducao;
import com.example.demo.producao.Producao;
import com.example.demo.producao.ProducaoRepository;
import com.example.demo.producao.ProducaoService;
import com.example.demo.usuario.Usuario;
import com.example.demo.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producao")
public class ProducaoController {

    @Autowired
    private ProducaoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProducaoService producaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DTOProducao> producaoCadastro(@RequestBody @Valid DTOProducao producaoDTO, UriComponentsBuilder uriBuilder) {
        var usuarioCriadorOptional = usuarioRepository.findById(producaoDTO.id_usuario_criador());
        if (usuarioCriadorOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var usuarioCriador = usuarioCriadorOptional.get();

        var producao = new Producao();
        producao.setTitulo(producaoDTO.titulo());
        producao.setDescricao(producaoDTO.descricao());
        producao.setData_publicacao(producaoDTO.data_publicacao());
        producao.setTipo_conteudo(producaoDTO.tipo_conteudo());
        producao.setIdUsuarioCriador(usuarioCriador);

        usuarioCriador.setProducao(producao);
        repository.save(producao);

        var uri = uriBuilder.path("/producao/{id_producao}").buildAndExpand(producao.getId_producao()).toUri();

        return ResponseEntity.created(uri).body(new DTOProducao(producao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOProducao> buscarProducao(@PathVariable Long id) {
        Optional<Producao> producao = repository.findById(id);

        if (producao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DTOProducao(producao.orElse(null)));
    }

    @GetMapping("/producoesUsuario")
    public ResponseEntity<List<Producao>> buscarProdutosUsuario(@RequestParam Long id_usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id_usuario);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var usuario = usuarioOptional.get();

        List<Producao> producaoList = repository.findByIdUsuarioCriador(usuario);

        return ResponseEntity.ok(producaoList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOProducao> atualizarProducao(@RequestBody @Valid DTOProducao producaoAtualizar) {
        var producao = repository.findById(producaoAtualizar.id_producao()).orElse(null);
        producaoService.atualizarProducao(producao, producaoAtualizar);

        return ResponseEntity.ok(new DTOProducao(producao)); // TODO: CKECK PARA NULIDADE

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DTOProducao> deletarProducao(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}



