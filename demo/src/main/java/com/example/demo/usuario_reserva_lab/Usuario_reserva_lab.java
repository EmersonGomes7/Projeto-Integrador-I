package com.example.demo.usuario_reserva_lab;


import com.example.demo.laboratorio.Laboratorio;
import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Usuario_reserva_lab")
@Entity(name = "usuario_reserva_lab")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_reserva")
public class Usuario_reserva_lab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id_reserva;

    @Column(name = "data_reserva", nullable = false)
    private LocalDateTime data_reserva;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime data_inicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime data_fim;

    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime hora_inicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalDateTime hora_fim;

    @Column(name = "status_reserva", nullable = false)
    private String status_reserva; // Da para fazer um Enum aqui? acho que fica mais organizado

    @Column(name = "lab_frequencia", nullable = false)
    private String lab_frequencia; // Acho que é desnecessário essa frequência qui, sendo que ja tem no Frequencia

    @Column(name = "motivo_reserva", nullable = false)
    private String motivo_reserva;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lab_reservado", nullable = false)
    private Laboratorio id_lab_reservado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_solicitante", nullable = false)
    private Usuario id_solicitante;
}
