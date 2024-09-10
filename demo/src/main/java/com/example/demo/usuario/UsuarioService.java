package com.example.demo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario buscarUsuarioPorId(Long id) {
        if (repository.existsById(id)) {
            return repository.getReferenceById(id);
        }
        return null;
    }

    public void atualizarInformacoes(Usuario usuario, DTODadosUsuario dados) {
        if (dados.nome() != null){
            usuario.setNome_usuario(dados.nome());
        }
        if (dados.tipo_usuario() != null){
            usuario.setTipo_usuario(dados.tipo_usuario());
        }
    }
}
