package com.stock.analysis.moex.integration.domain;

import com.stock.analysis.moex.integration.dto.Security;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface SecurityRepository {
    @Transactional
    public void insRow(Security security);
    @Transactional
    public List<Security> findAllSecurityDataByDate(LocalDate date);
    @Transactional
    public Security findOneSecurityByNameOnDate(LocalDate date, String name);



}
