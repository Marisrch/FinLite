package br.com.finlite.controller;

import br.com.finlite.dto.LinhaRelatorio;
import br.com.finlite.model.LancamentoFinanceiro;
import br.com.finlite.model.Venda;
import br.com.finlite.repository.LancamentoFinanceiroRepository;
import br.com.finlite.repository.MovimentacaoEstoqueRepository;
import br.com.finlite.repository.VendaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

    private final VendaRepository vendaRepository;
    private final LancamentoFinanceiroRepository lancamentoRepository;
    private final MovimentacaoEstoqueRepository movimentacaoRepository;

    public RelatorioController(VendaRepository vendaRepository,
                               LancamentoFinanceiroRepository lancamentoRepository,
                               MovimentacaoEstoqueRepository movimentacaoRepository) {
        this.vendaRepository = vendaRepository;
        this.lancamentoRepository = lancamentoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @GetMapping
    public String gerar(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Model model) {

        // Mesmo período padrão do painel
        LocalDate hoje = LocalDate.now();
        if (dataInicio == null) dataInicio = hoje.with(TemporalAdjusters.firstDayOfMonth());
        if (dataFim == null) dataFim = hoje;

        List<Venda> vendas = vendaRepository
                .findByStatusVendaIgnoreCaseAndDataRegistroBetween("CONCLUÍDA", dataInicio, dataFim);
        List<LancamentoFinanceiro> receitas = lancamentoRepository
                .findByTipoLancamentoIgnoreCaseAndDataLancamentoBetween("RECEITA", dataInicio, dataFim);
        List<LancamentoFinanceiro> despesas = lancamentoRepository
                .findByTipoLancamentoIgnoreCaseAndDataLancamentoBetween("DESPESA", dataInicio, dataFim);

        // Junta tudo em uma lista única
        List<LinhaRelatorio> linhas = new ArrayList<>();

        for (Venda v : vendas) {
            linhas.add(new LinhaRelatorio(v.getDataRegistro(), "Receita",
                    "Venda: " + v.getProdutoPeca() + " (" + v.getNomeCliente() + ")",
                    "Vendas", v.getValorVenda()));
        }
        for (LancamentoFinanceiro r : receitas) {
            linhas.add(new LinhaRelatorio(r.getDataLancamento(), "Receita",
                    r.getDescricaoLancamento(), r.getCategoriaLancamento(),
                    r.getValorTotalLancamento()));
        }
        for (LancamentoFinanceiro d : despesas) {
            linhas.add(new LinhaRelatorio(d.getDataLancamento(), "Despesa",
                    d.getDescricaoLancamento(), d.getCategoriaLancamento(),
                    d.getValorTotalLancamento()));
        }

        linhas.sort(Comparator.comparing(LinhaRelatorio::data,
                Comparator.nullsLast(Comparator.naturalOrder())));

        // Totais calculados a partir das próprias linhas listadas
        BigDecimal totalReceitas = somar(linhas, "Receita");
        BigDecimal totalDespesas = somar(linhas, "Despesa");

        // Movimentações de estoque do período (data + hora)
        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(LocalTime.MAX);

        model.addAttribute("linhas", linhas);
        model.addAttribute("totalReceitas", totalReceitas);
        model.addAttribute("totalDespesas", totalDespesas);
        model.addAttribute("saldo", totalReceitas.subtract(totalDespesas));
        model.addAttribute("movimentacoesEstoque", movimentacaoRepository
                .findByDataMovimentacaoBetweenOrderByDataMovimentacaoDesc(inicio, fim));
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("geradoEm", LocalDateTime.now());

        return "relatorio/index";
    }

    private BigDecimal somar(List<LinhaRelatorio> linhas, String tipo) {
        return linhas.stream()
                .filter(l -> tipo.equals(l.tipo()))
                .map(LinhaRelatorio::valor)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
