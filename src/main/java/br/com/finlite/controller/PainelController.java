
package br.com.finlite.controller;

import br.com.finlite.model.LancamentoFinanceiro;
import br.com.finlite.model.Material;
import br.com.finlite.model.Venda;
import br.com.finlite.repository.LancamentoFinanceiroRepository;
import br.com.finlite.repository.MaterialRepository;
import br.com.finlite.repository.VendaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class PainelController {

    private final VendaRepository vendaRepository;
    private final LancamentoFinanceiroRepository lancamentoRepository;
    private final MaterialRepository materialRepository;

    public PainelController(
            VendaRepository vendaRepository,
            LancamentoFinanceiroRepository lancamentoRepository,
            MaterialRepository materialRepository) {

        this.vendaRepository = vendaRepository;
        this.lancamentoRepository = lancamentoRepository;
        this.materialRepository = materialRepository;
    }

    @GetMapping("/painel")
    public String painel(Model model) {

        // Somente vendas concluídas entram nas receitas.
        List<Venda> vendasConcluidas =
                vendaRepository.findByStatusVendaIgnoreCase("CONCLUÍDA");

        // Receitas adicionais e despesas.
        List<LancamentoFinanceiro> outrasReceitas =
                lancamentoRepository.findByTipoLancamentoIgnoreCase(
                        "RECEITA"
                );

        List<LancamentoFinanceiro> despesas =
                lancamentoRepository.findByTipoLancamentoIgnoreCase(
                        "DESPESA"
                );

        BigDecimal totalVendas = vendasConcluidas.stream()
                .map(Venda::getValorVenda)
                .filter(valor -> valor != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalOutrasReceitas = outrasReceitas.stream()
                .map(LancamentoFinanceiro::getValorTotalLancamento)
                .filter(valor -> valor != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesas = despesas.stream()
                .map(LancamentoFinanceiro::getValorTotalLancamento)
                .filter(valor -> valor != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalReceitas =
                totalVendas.add(totalOutrasReceitas);

        BigDecimal saldo =
                totalReceitas.subtract(totalDespesas);

        // Indicadores de estoque.
        List<Material> materiais = materialRepository.findAll();

        List<Material> materiaisBaixoEstoque = materiais.stream()
                .filter(material -> {
                    BigDecimal quantidade =
                            material.getQuantidadeEstoqueMaterial();

                    BigDecimal minimo =
                            material.getQuantidadeMinimaMaterial();

                    return quantidade != null &&
                            minimo != null &&
                            quantidade.compareTo(minimo) <= 0;
                })
                .toList();

        BigDecimal valorEstoque = materiais.stream()
                .map(material -> {
                    BigDecimal quantidade =
                            material.getQuantidadeEstoqueMaterial();

                    BigDecimal custo =
                            material.getCustoUnitarioMaterial();

                    if (quantidade == null || custo == null) {
                        return BigDecimal.ZERO;
                    }

                    return quantidade.multiply(custo);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("totalVendas", totalVendas);
        model.addAttribute("totalOutrasReceitas", totalOutrasReceitas);
        model.addAttribute("totalReceitas", totalReceitas);
        model.addAttribute("totalDespesas", totalDespesas);
        model.addAttribute("saldo", saldo);

        model.addAttribute("quantidadeMateriais", materiais.size());
        model.addAttribute(
                "quantidadeMateriaisBaixoEstoque",
                materiaisBaixoEstoque.size()
        );
        model.addAttribute("materiaisBaixoEstoque", materiaisBaixoEstoque);
        model.addAttribute("valorEstoque", valorEstoque);

        return "painel/index";
    }
}
