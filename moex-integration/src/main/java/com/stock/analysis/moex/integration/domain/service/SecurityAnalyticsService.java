package com.stock.analysis.moex.integration.domain.service;

import com.stock.analysis.moex.integration.dto.SecurityPriceDifference;

import java.time.LocalDate;
import java.util.List;

public interface SecurityAnalyticsService {

    public void addSecOnPrevWorkDay() throws Exception;
    public List<SecurityPriceDifference> getTopSecurityAtTime(LocalDate beginDate, LocalDate endDate, int topCount, boolean isIncrease);

}
