
package br.com.finlite.repository;

import br.com.finlite.model.LancamentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;

public interface LancamentoFinanceiroRepository
        extends JpaRepository<LancamentoFinanceiro, Integer> {

    List<LancamentoFinanceiro> findByTipoLancamentoIgnoreCase(
            String tipoLancamento
    );

    List<LancamentoFinanceiro> findByTipoLancamentoIgnoreCaseOrderByDataLancamentoDesc(
            String tipoLancamento
    );

    List<LancamentoFinanceiro> findByTipoLancamentoIgnoreCaseAndDataLancamentoBetween(
            String tipoLancamento,
            LocalDate dataInicio,
            LocalDate dataFim
    );

    boolean existsByMaterialIdMaterial(Integer idMaterial);
}
