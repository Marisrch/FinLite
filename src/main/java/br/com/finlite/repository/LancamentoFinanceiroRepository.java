
package br.com.finlite.repository;

import br.com.finlite.model.LancamentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoFinanceiroRepository
        extends JpaRepository<LancamentoFinanceiro, Integer> {

    List<LancamentoFinanceiro> findByTipoLancamentoIgnoreCase(
            String tipoLancamento
    );
}
