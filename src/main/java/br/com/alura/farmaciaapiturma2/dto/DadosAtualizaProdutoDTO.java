package br.com.alura.farmaciaapiturma2.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaProdutoDTO(
        @NotNull
        int id,
        String descricaoProduto,
        double precoProduto) {
}
