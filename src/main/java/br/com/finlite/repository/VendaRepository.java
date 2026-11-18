
package br.com.finlite.repository;

import br.com.finlite.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    // Pesquisa por cliente e/ou status.
    @Query("""
            SELECT v FROM Venda v
            WHERE (:cliente IS NULL OR
                   LOWER(v.nomeCliente) LIKE LOWER(CONCAT('%', :cliente, '%')))
            AND (:status IS NULL OR
                 LOWER(v.statusVenda) = LOWER(:status))
            ORDER BY v.dataRegistro DESC
            """)
    List<Venda> pesquisar(
            @Param("cliente") String cliente,
            @Param("status") String status
    );

    // Consulta por status, mantida para as funcionalidades existentes.
    List<Venda> findByStatusVendaIgnoreCase(String statusVenda);

    List<Venda> findByStatusVendaIgnoreCaseAndDataRegistroBetween(
            String statusVenda,
            LocalDate dataInicio,
            LocalDate dataFim
    );
}
