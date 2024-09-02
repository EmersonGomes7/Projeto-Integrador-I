package com.example.demo.controllers;

import com.example.demo.usuario.DTODadosUsuario;
import com.example.demo.usuario.Usuario;
import com.example.demo.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<DTODadosUsuario> usuarioCadastro(@RequestBody @Valid DTODadosUsuario usuarioDTO, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(usuarioDTO);
        repository.save(usuario);

        var uri = uriBuilder.path("/usuario/{id_publi}").buildAndExpand(usuario.getId_usuario()).toUri();

        return ResponseEntity.created(uri).body(new DTODadosUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<DTODadosUsuario>> listarUsuario(){
        var lista = repository.findAll().stream().map(DTODadosUsuario::new).toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODadosUsuario> buscarUsuarioPorId(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);

        return ResponseEntity.ok(new DTODadosUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTODadosUsuario> atualizar(@RequestBody @Valid DTODadosUsuario usuarioAtualizar){
         var usuario = repository.getReferenceById(usuarioAtualizar.id());
         usuario.atualizarInformacoes(usuarioAtualizar);

         return ResponseEntity.ok(new DTODadosUsuario(usuario));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DTODadosUsuario> deletarUsuario(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
