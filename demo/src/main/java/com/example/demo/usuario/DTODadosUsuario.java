package com.example.demo.usuario;

public record DTODadosUsuario(
        Long id,
        String nome,
        TipoUsuario tipo_usuario
) {

    public DTODadosUsuario(Usuario usuario){
        this(
                usuario.getId_usuario(),
                usuario.getNome_usuario(),
                usuario.getTipo_usuario()
        );
    }
}
