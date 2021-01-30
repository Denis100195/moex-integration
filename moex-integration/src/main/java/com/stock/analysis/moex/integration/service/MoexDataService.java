package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.client.BusinessCalendarClient;
import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.repository.SecurityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MoexDataService {
    private XmlParserService xmlParserService;
    private BusinessCalendarClient businessCalendarClient;
    private SecurityRepository securityRepository;


    @Autowired
    public MoexDataService(SecurityRepository securityRepository, XmlParserService xmlParserService){
        this.securityRepository = securityRepository;
        this.xmlParserService = xmlParserService;
    }
    //метод, который сохраняет данные с сайта в базу на дату, переданную в параметры
    public void saveSecuritiesOnDate(LocalDate date) throws Exception{
        List<Security> secOnDate = xmlParserService.getSecuritiesOnDateFromMoex(date);
        for (int i = 0; i < secOnDate.size(); i++) {
            securityRepository.insRow(secOnDate.get(i));
        }
    }
    //метод котор возращает даннные из базы на определенную дату
    public List<Security> getSecurityDataOnDate(LocalDate date) {
        return securityRepository.findAllSecurityDataByDate(date);
    }
    //топ 10 бумаг с максимальным объемом, у которых цена (high) больше 200. с использ стримов
    public List<Security> getTopSecurity(final LocalDate date){
        return getSecurityDataOnDate(date)
                .stream()
                .filter(security -> security.getHigh().compareTo(BigDecimal.valueOf(200)) > 0)
                .sorted(Comparator.comparing(Security::getHigh))
                .limit(10)
                .collect(Collectors.toList());
    }
    //метод который возвращает данные по одной бумаге
    public Security getOneSecurityByNameOnDate(LocalDate date, String shName) {
        Security sec = securityRepository.findOneSecurityByNameOnDate(date, shName);
        return sec;
    }
}
