package br.com.alura.farmaciaapiturma2.model;

import br.com.alura.farmaciaapiturma2.dto.DadosAtualizaProdutoDTO;
import br.com.alura.farmaciaapiturma2.dto.DadosCadastroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    @ManyToOne
    private Fabricante fabricante;

    public Produto(DadosCadastroDTO dadosCadastro, Fabricante fabricante) {

    }

    public void atulizar(DadosAtualizaProdutoDTO produtoDTO) {
        if (produtoDTO.descricaoProduto() != null){
            this.descricao = produtoDTO.descricaoProduto();
        }
        if (produtoDTO.precoProduto() > 0){
            this.preco = produtoDTO.precoProduto();
        }
    }
}
