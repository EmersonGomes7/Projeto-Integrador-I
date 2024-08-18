package com.example.demo.frequencia;

import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Table(name = "Frequencia")
@Entity(name = "frequencias")
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

    @Column(name = "lab_frequencia", nullable = false)
    private String lab_frequencia; // Vai ser uma string com a frequência das pessoas

    @Column(name = "tipo_de_usua", nullable = false)
    private String tipo_de_usua;

    @OneToOne(cascade = CascadeType.ALL) // Checar
    @JoinColumn(name = "id_usuario_prof", nullable = false)
    private Usuario id_usuario_prof;

}
