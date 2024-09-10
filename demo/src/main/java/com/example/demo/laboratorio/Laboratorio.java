package com.example.demo.laboratorio;


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

    public Laboratorio(DTOLaboratorio laboratorioDTO) {
        this.id_lab = laboratorioDTO.id();
        this.nome_lab = laboratorioDTO.nome_lab();
        this.capacidade = laboratorioDTO.capacidade();
    }


}
