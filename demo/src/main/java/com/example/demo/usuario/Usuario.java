package com.example.demo.usuario;


import com.example.demo.frequencia.Frequencia;
import com.example.demo.producao.Producao;
import com.example.demo.publicacao.Publicacao;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "Usuario")
@Entity(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(name = "nome", nullable = false)
    private String nome_usuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo_usuario;



    @OneToMany(mappedBy = "idUsuarioCriador", cascade = CascadeType.REMOVE) // Produções associadas
    private List<Producao> producoes;

    @OneToMany(mappedBy = "idUsuarioCriador", cascade = CascadeType.REMOVE) // Publicações associadas
    private List<Publicacao> publicacao;

    @OneToMany(mappedBy = "idUsuarioProf", cascade = CascadeType.REMOVE) // Frequências associadas
    private List<Frequencia> frequencia;

    public Usuario(DTODadosUsuario usuarioDTO) {
        this.id_usuario = usuarioDTO.id();
        this.nome_usuario = usuarioDTO.nome();
        this.tipo_usuario = usuarioDTO.tipo_usuario();
    }

    public void setPublicacao(Publicacao publi){
        this.publicacao.add(publi);
    }
    public void setProducao(Producao prod){
        this.producoes.add(prod);
    }
    public void setFrequencia(Frequencia freq){
        this.frequencia.add(freq);
    }
}
