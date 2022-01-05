package com.stock.analysis.moex.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityPriceDifference {
    private String secId;
    private String shortName;
    private BigDecimal difference;
}
