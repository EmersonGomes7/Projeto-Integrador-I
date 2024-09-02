package com.example.demo.producao;

import com.example.demo.producao.Producao;
import com.example.demo.producao.TipoCont;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DTOProducao(
        Long id_producao,
        TipoCont tipo_conteudo,
        String titulo,
        String descricao,
        LocalDate data_publicacao,
        Long id_usuario_criador // Adicionando validação de não nulo
) {
    public DTOProducao(Producao producao) {
        this(
                producao.getId_producao(),
                producao.getTipo_conteudo(),
                producao.getTitulo(),
                producao.getDescricao(),
                producao.getData_publicacao(),
                producao.getId_do_usuario().getId_usuario()
        );
    }
}
