package com.example.demo.publicacao;

import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Publicacao")
@Entity(name = "publicacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_publi")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publi;

    @Column(name = "rede_social", nullable = false)
    @Enumerated(EnumType.STRING)
    private RedeSocial rede_social;

    @Column(name = "data_publi", nullable = false)
    private LocalDate data_publi;

    @OneToOne
    @JoinColumn(name = "id_usuario_criador", nullable = false)
    private Usuario id_usuario_criador;
}
