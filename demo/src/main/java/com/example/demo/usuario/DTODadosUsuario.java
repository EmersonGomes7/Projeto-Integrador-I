package com.example.demo.usuario;

import jakarta.persistence.Enumerated;

public record DTODadosUsuario(
        Long id,
        String nome,
        @Enumerated
        TipoUsuario tipo_usuario,
        String email
) {

    public DTODadosUsuario(Usuario usuario){
        this(
                usuario.getIdUsuario(),
                usuario.getNome_usuario(),
                usuario.getTipo_usuario(),
                usuario.getEmail()
        );
    }


}