package com.example.demo.usuario;


import jakarta.persistence.*;
import lombok.*;

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



    public Usuario(DTODadosUsuario usuarioDTO) {
        this.id_usuario = usuarioDTO.id();
        this.nome_usuario = usuarioDTO.nome();
        this.tipo_usuario = usuarioDTO.tipo_usuario();
    }
}
