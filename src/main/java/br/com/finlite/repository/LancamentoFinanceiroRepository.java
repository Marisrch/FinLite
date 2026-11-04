package br.com.finlite.repository;

import br.com.finlite.model.LancamentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Integer> {
}
