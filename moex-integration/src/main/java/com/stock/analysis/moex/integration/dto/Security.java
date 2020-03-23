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
    private BigDecimal numTrades; //TODO вместо BigDecimal лучше использовать BigDecimal. Короче никогда BigDecimal не используй
    // вот две статьи с примерами, почему это плохо
    // https://computingat40s.wordpress.com/java-float-and-BigDecimal-primitive-types-are-evil-dont-use-them/
    // https://stackoverflow.com/questions/3730019/why-not-use-BigDecimal-or-float-to-represent-currency
    private BigDecimal value;
    private BigDecimal open;
    private BigDecimal low;
    private BigDecimal high; // TODO high
    private BigDecimal legalClosePrice;
    private BigDecimal waPrice;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal marketPrice2, marketPrice3;
    private BigDecimal admittedQoute;
    private BigDecimal mp2ValTrd;
    private BigDecimal marketPrice3TradeValue;
    private BigDecimal admittedValue;
    private BigDecimal waval;

    // TODO тут геттеры и сеттеры не нужны, lombok ведь используем
    // TODO почитай эту статью https://habr.com/ru/post/345520/

    
}
