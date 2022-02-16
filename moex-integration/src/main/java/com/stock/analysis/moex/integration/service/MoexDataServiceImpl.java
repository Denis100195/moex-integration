package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.domain.service.MoexDataService;
import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.repository.SecurityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MoexDataServiceImpl implements MoexDataService {
    private XmlParserServiceImpl xmlParserServiceImpl;
    private SecurityRepositoryImpl securityRepositoryImpl;


    @Autowired
    public MoexDataServiceImpl(SecurityRepositoryImpl securityRepositoryImpl, XmlParserServiceImpl xmlParserServiceImpl){
        this.securityRepositoryImpl = securityRepositoryImpl;
        this.xmlParserServiceImpl = xmlParserServiceImpl;
    }
    //метод, который сохраняет данные с сайта в базу на дату, переданную в параметры
    @Transactional
    public void saveSecuritiesOnDate(LocalDate date) throws Exception{
        List<Security> secOnDate = xmlParserServiceImpl.getSecuritiesOnDateFromMoex(date);
        for (int i = 0; i < secOnDate.size(); i++) {
            securityRepositoryImpl.insRow(secOnDate.get(i));
        }
    }
    //метод котор возращает даннные из базы на определенную дату
    @Transactional
    public List<Security> getSecurityDataOnDate(LocalDate date) {
        return securityRepositoryImpl.findAllSecurityDataByDate(date);
    }
    //топ 10 бумаг с максимальным объемом, у которых цена (high) больше 200. с использ стримов
    @Transactional
    public List<Security> getTopSecurity(final LocalDate date){
        return getSecurityDataOnDate(date)
                .stream()
                .filter(security -> security.getHigh().compareTo(BigDecimal.valueOf(200)) > 0)
                .sorted(Comparator.comparing(Security::getHigh))
                .limit(10)
                .collect(Collectors.toList());
    }
    //метод который возвращает данные по одной бумаге
    @Transactional
    public Security getOneSecurityByNameOnDate(LocalDate date, String shName) {
        Security sec = securityRepositoryImpl.findOneSecurityByNameOnDate(date, shName);
        return sec;
    }
}
