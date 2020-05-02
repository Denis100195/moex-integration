package com.stock.analysis.moex.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Security {

    private String boardId;
    private LocalDate tradeDate;
    private String shortName;
    private String secId;
    private BigDecimal numTrades;
    private BigDecimal value;
    private BigDecimal open;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal legalClosePrice;
    private BigDecimal waPrice;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal marketPrice2;
    private BigDecimal marketPrice3;
    private BigDecimal admittedQoute;
    private BigDecimal mp2ValTrd;
    private BigDecimal marketPrice3TradeValue;
    private BigDecimal admittedValue;
    private BigDecimal waval;

}
