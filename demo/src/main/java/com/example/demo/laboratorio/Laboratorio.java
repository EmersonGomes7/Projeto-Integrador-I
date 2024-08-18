package com.example.demo.laboratorio;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Laboratorio")
@Entity(name = "Laboratorios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_lab")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lab;

    @Column(name = "nome_lab", nullable = false)
    private String nome_lab;

    @Column(name = "capacidade", nullable = false)
    private int capacidade;

}
