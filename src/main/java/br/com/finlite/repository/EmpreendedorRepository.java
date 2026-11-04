package br.com.finlite.repository;

import br.com.finlite.model.Empreendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpreendedorRepository extends JpaRepository<Empreendedor, Integer> {
}
