
package br.com.finlite.controller;

import br.com.finlite.model.LancamentoFinanceiro;
import br.com.finlite.model.Material;
import br.com.finlite.model.Venda;
import br.com.finlite.repository.LancamentoFinanceiroRepository;
import br.com.finlite.repository.MaterialRepository;
import br.com.finlite.repository.VendaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Comparator;

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
    public String painel(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicio,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFim,

            Model model) {

        // Por padrão, considera o mês atual.
        LocalDate hoje = LocalDate.now();

        if (dataInicio == null) {
            dataInicio = hoje.with(TemporalAdjusters.firstDayOfMonth());
        }

        if (dataFim == null) {
            dataFim = hoje;
        }

        // Busca somente as movimentações financeiras do período.
        List<Venda> vendasConcluidas =
                vendaRepository.findByStatusVendaIgnoreCaseAndDataRegistroBetween(
                        "CONCLUÍDA",
                        dataInicio,
                        dataFim
                );

        List<LancamentoFinanceiro> outrasReceitas =
                lancamentoRepository
                        .findByTipoLancamentoIgnoreCaseAndDataLancamentoBetween(
                                "RECEITA",
                                dataInicio,
                                dataFim
                        );

        List<LancamentoFinanceiro> despesas =
                lancamentoRepository
                        .findByTipoLancamentoIgnoreCaseAndDataLancamentoBetween(
                                "DESPESA",
                                dataInicio,
                                dataFim
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

        // Vendas concluídas + receitas adicionais, sem duplicação.
        BigDecimal totalReceitas =
                totalVendas.add(totalOutrasReceitas);

        BigDecimal saldo =
                totalReceitas.subtract(totalDespesas);

        // Prepara os dados do gráfico de evolução financeira.
        boolean agruparPorMes =
                ChronoUnit.DAYS.between(dataInicio, dataFim) > 31;

        LocalDate inicioGrafico = agruparPorMes
                ? dataInicio.withDayOfMonth(1)
                : dataInicio;

        LocalDate fimGrafico = agruparPorMes
                ? dataFim.withDayOfMonth(1)
                : dataFim;

        Map<LocalDate, BigDecimal> receitasPorPeriodo =
                new LinkedHashMap<>();

        Map<LocalDate, BigDecimal> despesasPorPeriodo =
                new LinkedHashMap<>();

        List<String> labelsGrafico = new ArrayList<>();

        DateTimeFormatter formatoData = agruparPorMes
                ? DateTimeFormatter.ofPattern("MM/yyyy")
                : DateTimeFormatter.ofPattern("dd/MM");

// Cria todos os períodos, inclusive aqueles sem movimentações.
        for (LocalDate periodo = inicioGrafico;
             !periodo.isAfter(fimGrafico);
             periodo = agruparPorMes
                     ? periodo.plusMonths(1)
                     : periodo.plusDays(1)) {

            receitasPorPeriodo.put(periodo, BigDecimal.ZERO);
            despesasPorPeriodo.put(periodo, BigDecimal.ZERO);

            labelsGrafico.add(periodo.format(formatoData));
        }

// Agrupa as vendas concluídas.
        for (Venda venda : vendasConcluidas) {
            if (venda.getDataRegistro() != null &&
                    venda.getValorVenda() != null) {

                LocalDate periodo = venda.getDataRegistro();

                if (agruparPorMes) {
                    periodo = periodo.withDayOfMonth(1);
                }

                receitasPorPeriodo.merge(
                        periodo,
                        venda.getValorVenda(),
                        BigDecimal::add
                );
            }
        }

// Agrupa as receitas adicionais.
        for (LancamentoFinanceiro receita : outrasReceitas) {
            if (receita.getDataLancamento() != null &&
                    receita.getValorTotalLancamento() != null) {

                LocalDate periodo = receita.getDataLancamento();

                if (agruparPorMes) {
                    periodo = periodo.withDayOfMonth(1);
                }

                receitasPorPeriodo.merge(
                        periodo,
                        receita.getValorTotalLancamento(),
                        BigDecimal::add
                );
            }
        }

// Agrupa as despesas.
        for (LancamentoFinanceiro despesa : despesas) {
            if (despesa.getDataLancamento() != null &&
                    despesa.getValorTotalLancamento() != null) {

                LocalDate periodo = despesa.getDataLancamento();

                if (agruparPorMes) {
                    periodo = periodo.withDayOfMonth(1);
                }

                despesasPorPeriodo.merge(
                        periodo,
                        despesa.getValorTotalLancamento(),
                        BigDecimal::add
                );
            }
        }

        List<BigDecimal> valoresReceitasGrafico =
                new ArrayList<>(receitasPorPeriodo.values());

        List<BigDecimal> valoresDespesasGrafico =
                new ArrayList<>(despesasPorPeriodo.values());

        // Agrupa a receita das vendas concluídas por produto/peça.
        Map<String, BigDecimal> faturamentoPorProduto =
                new LinkedHashMap<>();

        for (Venda venda : vendasConcluidas) {

            String produto = venda.getProdutoPeca();

            if (produto != null && !produto.isBlank()
                    && venda.getValorVenda() != null) {

                faturamentoPorProduto.merge(
                        produto.trim(),
                        venda.getValorVenda(),
                        BigDecimal::add
                );
            }
        }

// Ordena os produtos do maior para o menor faturamento.
        List<Map.Entry<String, BigDecimal>> produtosOrdenados =
                faturamentoPorProduto.entrySet()
                        .stream()
                        .sorted(
                                Map.Entry.<String, BigDecimal>comparingByValue(
                                        Comparator.reverseOrder()
                                )
                        )
                        .toList();

        List<String> labelsProdutosGrafico = produtosOrdenados
                .stream()
                .map(Map.Entry::getKey)
                .toList();

        model.addAttribute("produtosDisponiveis", labelsProdutosGrafico);

        List<BigDecimal> valoresProdutosGrafico = produtosOrdenados
                .stream()
                .map(Map.Entry::getValue)
                .toList();

        // Agrupa o valor das despesas por categoria.
        Map<String, BigDecimal> despesasPorCategoria =
                new LinkedHashMap<>();

        for (LancamentoFinanceiro despesa : despesas) {

            String categoria = despesa.getCategoriaLancamento();

            if (categoria == null || categoria.isBlank()) {
                categoria = "Sem categoria";
            }

            if (despesa.getValorTotalLancamento() != null) {
                despesasPorCategoria.merge(
                        categoria.trim(),
                        despesa.getValorTotalLancamento(),
                        BigDecimal::add
                );
            }
        }

// Ordena as categorias da maior para a menor despesa.
        List<Map.Entry<String, BigDecimal>> categoriasOrdenadas =
                despesasPorCategoria.entrySet()
                        .stream()
                        .sorted(
                                Map.Entry.<String, BigDecimal>comparingByValue(
                                        Comparator.reverseOrder()
                                )
                        )
                        .toList();

        List<String> labelsCategoriasGrafico = categoriasOrdenadas
                .stream()
                .map(Map.Entry::getKey)
                .toList();

        List<BigDecimal> valoresCategoriasGrafico = categoriasOrdenadas
                .stream()
                .map(Map.Entry::getValue)
                .toList();

        // Estoque permanece independente do período selecionado.
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

        // Mantém as datas selecionadas disponíveis no HTML.
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("labelsGrafico", labelsGrafico);
        model.addAttribute("valoresReceitasGrafico", valoresReceitasGrafico);
        model.addAttribute("valoresDespesasGrafico", valoresDespesasGrafico);
        model.addAttribute("labelsProdutosGrafico", labelsProdutosGrafico);
        model.addAttribute("valoresProdutosGrafico", valoresProdutosGrafico);
        model.addAttribute("labelsCategoriasGrafico", labelsCategoriasGrafico);
        model.addAttribute("valoresCategoriasGrafico", valoresCategoriasGrafico);

        return "painel/index";
    }
}
