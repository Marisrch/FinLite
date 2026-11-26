package br.com.finlite.repository;

import br.com.finlite.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Integer> {

    boolean existsByMaterialIdMaterial(Integer idMaterial);

    List<MovimentacaoEstoque> findByDataMovimentacaoBetweenOrderByDataMovimentacaoDesc(
            LocalDateTime inicio, LocalDateTime fim);
}
