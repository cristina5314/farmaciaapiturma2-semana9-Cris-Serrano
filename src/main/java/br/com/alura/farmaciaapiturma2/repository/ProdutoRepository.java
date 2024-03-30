package br.com.alura.farmaciaapiturma2.repository;

import br.com.alura.farmaciaapiturma2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
