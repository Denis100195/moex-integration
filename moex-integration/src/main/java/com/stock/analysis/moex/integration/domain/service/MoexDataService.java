package com.stock.analysis.moex.integration.domain.service;

import com.stock.analysis.moex.integration.dto.Security;

import java.time.LocalDate;
import java.util.List;

public interface MoexDataService {

    public void saveSecuritiesOnDate(LocalDate date) throws Exception;
    public List<Security> getSecurityDataOnDate(LocalDate date);
    public Security getOneSecurityByNameOnDate(LocalDate date, String shName);
    public List<Security> getTopSecurity(final LocalDate date);
}
