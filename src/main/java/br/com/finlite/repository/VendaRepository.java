
package br.com.finlite.repository;

import br.com.finlite.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    List<Venda> findByStatusVendaIgnoreCase(String statusVenda);
}
