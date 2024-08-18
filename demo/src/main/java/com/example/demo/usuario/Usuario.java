package com.example.demo.usuario;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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

    @Column(nullable = false)
    private String nome_usuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo_usuario;


}
