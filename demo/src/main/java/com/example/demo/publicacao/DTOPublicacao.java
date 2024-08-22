package com.example.demo.publicacao;

import com.example.demo.usuario.Usuario;

import java.time.LocalDate;
import java.util.Optional;

public record DTOPublicacao(
        Long id_publi,
        RedeSocial rede_social,
        LocalDate data_publi,
        Long id_usuario_criador
) {

    public DTOPublicacao(Publicacao publicacao) {
        this(
                publicacao.getId_publi(),
                publicacao.getRede_social(),
                publicacao.getData_publi(),
                publicacao.getId_usuario_criador().getId_usuario()
        );
    }


    public DTOPublicacao(Long idPubli, RedeSocial redeSocial, LocalDate dataPubli, Usuario idUsuarioCriador) {
        this(
                idPubli,
                redeSocial,
                dataPubli,
                idUsuarioCriador.getId_usuario()
        );
    }
}
