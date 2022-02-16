package com.stock.analysis.moex.integration.domain.service;

import com.stock.analysis.moex.integration.dto.Security;

import java.time.LocalDate;
import java.util.List;

public interface XmlParserService {

    public List<Security> getSecuritiesOnDateFromMoex(LocalDate date) throws Exception;

}
