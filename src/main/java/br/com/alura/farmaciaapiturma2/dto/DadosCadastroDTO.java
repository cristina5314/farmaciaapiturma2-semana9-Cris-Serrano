package br.com.alura.farmaciaapiturma2.dto;

import br.com.alura.farmaciaapiturma2.model.Fabricante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DadosCadastroDTO(
        @NotBlank
        String nomeFabricante,
        @NotBlank
        String nomeProduto,
        @NotBlank
        String decricaoProduto,
        @Positive
        double precoProduto
) {
}
