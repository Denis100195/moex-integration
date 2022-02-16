package com.stock.analysis.moex.integration.domain.repository;

import com.stock.analysis.moex.integration.dto.Security;


import java.time.LocalDate;
import java.util.List;

public interface SecurityRepository {

    public void insRow(Security security);

    public List<Security> findAllSecurityDataByDate(LocalDate date);

    public Security findOneSecurityByNameOnDate(LocalDate date, String name);

}
