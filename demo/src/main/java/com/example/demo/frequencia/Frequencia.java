package com.example.demo.frequencia;

import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "Frequencia")
@Entity(name = "frequencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_frequencia")
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_frequencia;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "freq_alunos", nullable = false)
    private String freq_alunos; // Presente / Faltou

    @Column(name = "presenca_alunos", nullable = false)
    private String presenca_alunos;// Nome dos alunos

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario_prof")
    private Usuario idUsuarioProf;


}
