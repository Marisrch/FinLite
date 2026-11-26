package br.com.finlite.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LinhaRelatorio(

        LocalDate data,
        String tipo,
        String descricao,
        String categoria,
        BigDecimal valor) {
}
