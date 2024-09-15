package com.example.demo.usuario;


import com.example.demo.frequencia.Frequencia;
import com.example.demo.producao.Producao;
import com.example.demo.publicacao.Publicacao;
import com.example.demo.usuario_reserva_lab.Usuario_reserva_lab;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "Usuario")
@Entity(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nome", nullable = false)
    private String nome_usuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo_usuario;

    @Column(nullable = false)
    private String email;


    @OneToMany(mappedBy = "idUsuarioCriador", cascade = CascadeType.REMOVE) // Produções associadas
    private List<Producao> producoes;//Lista duplamente encadeada

    @OneToMany(mappedBy = "idUsuarioCriador", cascade = CascadeType.REMOVE) // Publicações associadas
    private List<Publicacao> publicacao;//Lista duplamente encadeada

    @OneToMany(mappedBy = "idUsuarioProf", cascade = CascadeType.REMOVE) // Frequências associadas
    private List<Frequencia> frequencia;//Lista duplamente encadeada

    @JsonIgnore
    @OneToOne(mappedBy = "idSolicitante", cascade = CascadeType.REMOVE)
    private Usuario_reserva_lab resevaUsuario;

    public Usuario(DTODadosUsuario usuarioDTO) {
        this.idUsuario = usuarioDTO.id();
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
