package com.example.demo.publicacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    //List<Publicacao> findById_usuario_criador(Optional<Usuario> usuario);
}
