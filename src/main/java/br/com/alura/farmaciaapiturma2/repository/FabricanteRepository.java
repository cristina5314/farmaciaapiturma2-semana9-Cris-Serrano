package br.com.alura.farmaciaapiturma2.repository;

import br.com.alura.farmaciaapiturma2.model.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {

    Fabricante findByNome(String s);
}
