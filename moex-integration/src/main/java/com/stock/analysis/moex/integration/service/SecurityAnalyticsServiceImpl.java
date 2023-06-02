package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.client.BusinessCalendarClient;
import com.stock.analysis.moex.integration.domain.service.SecurityAnalyticsService;
import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.dto.SecurityPriceDifference;
import com.stock.analysis.moex.integration.repository.SecurityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.stock.analysis.moex.integration.config.Constants.RUSSIA_CODE;

@Service
@Slf4j
public class SecurityAnalyticsServiceImpl implements SecurityAnalyticsService {
    private BusinessCalendarClient businessCalendarClient;
    private MoexDataServiceImpl moexDataServiceImpl;
    private SecurityRepositoryImpl securityRepositoryImpl;
    private final JdbcTemplate jdbcTemplate;

    private static final String ORDER_SECURITY_BY_PRICE_DIFFERENCE_test =
            "select s1.sec_id, s1.short_name, ((s2.close/s1.close-1)*100) dif " +
                    "from security s1 inner join security s2 on s1.sec_id=s2.sec_id " +
                    "where s1.trade_date='2020-12-28' and s2.trade_date='2020-12-29' and s1.close is not null and  s2.close is not null order by dif desc limit 20";

    private static final String ORDER_SECURITY_BY_PRICE_DIFFERENCE = "select s1.sec_id, s1.short_name, ((s2.close/s1.close-1)*100) dif from security s1 " +
            "inner join security s2 on s1.sec_id=s2.sec_id " +
            "where s1.trade_date = ? and s2.trade_date = ? and s1.close is not null and  s2.close is not null " +
            "order by dif";

    private RowMapper<SecurityPriceDifference> securityPriceDifferenceRowMapper = (resultSet, i) ->
            new SecurityPriceDifference(
                    resultSet.getString("sec_id"),
                    resultSet.getString("short_name"),
                    resultSet.getBigDecimal("dif")
            );

    @Autowired
    public SecurityAnalyticsServiceImpl(
            BusinessCalendarClient businessCalendarClient, MoexDataServiceImpl moexDataServiceImpl, SecurityRepositoryImpl securityRepositoryImpl, DataSource dataSource){
        this.businessCalendarClient = businessCalendarClient;
        this.moexDataServiceImpl = moexDataServiceImpl;
        this.securityRepositoryImpl = securityRepositoryImpl;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //метод сохраняет даныые в БД за предыдущий рабочий день по расписанию
    @Scheduled(cron = "${rates.loader.cron}")
    public void addSecOnPrevWorkDay() throws Exception{
        LocalDate workDay = businessCalendarClient.getPreviousWorkingDate(LocalDate.now(), RUSSIA_CODE);

        moexDataServiceImpl.saveSecuritiesOnDate(workDay);
        log.info("Security was added into database");

    }

    //топ акций, которые за определенное время выросли и упали
    public List<SecurityPriceDifference> getTopSecurityAtTime(LocalDate beginDate, LocalDate endDate, int topCount, boolean isIncrease){
        List<SecurityPriceDifference> query = jdbcTemplate.query(ORDER_SECURITY_BY_PRICE_DIFFERENCE +
                (isIncrease ? " asc" : " desc") + " limit " + topCount, securityPriceDifferenceRowMapper, beginDate, endDate);

        return query;
        //return jdbcTemplate.query(ORDER_SECURITY_BY_PRICE_DIFFERENCE_test, securityPriceDifferenceRowMapper, beginDate, endDate, topCount, isIncrease);

    }

    //группировка по num_trades
    public Map<Integer, List<Security>> getOrderedSecOnNmTr(){
        List<Security> myList = securityRepositoryImpl.findAllSecurityData();
        Map<Integer, List<Security>> groupByNumTr =
                myList
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        sec -> sec.getNumTrades().intValue()
                                )
                        );
        return groupByNumTr;

    }
}
