package com.stock.analysis.moex.integration.repository;

import com.stock.analysis.moex.integration.dto.Security;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class SecurityRepository {
    private final JdbcTemplate jdbcTemplate;

    public SecurityRepository(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String SELECT_SECURITY_BY_DATE = "select * from security where trade_date = ?";
    private static final String INSERT_SECURITY_INTO_DB = "INSERT INTO security values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_ONE_SECURITY_BY_NAME_ON_DATE = "select * from security where trade_date = ? and short_name = ?";

    private RowMapper<Security> securityRowMapper = (resultSet, i) ->
         new Security(
                resultSet.getString("board_id"),
                resultSet.getTimestamp("trade_date").toLocalDateTime().toLocalDate(),
                resultSet.getString("short_name"),
                resultSet.getString("sec_id"),
                resultSet.getBigDecimal("num_trades"),
                resultSet.getBigDecimal("value"),
                resultSet.getBigDecimal("open"),
                resultSet.getBigDecimal("low"),
                resultSet.getBigDecimal("high"),
                resultSet.getBigDecimal("legal_close_price"),
                resultSet.getBigDecimal("wa_price"),
                resultSet.getBigDecimal("close"),
                resultSet.getBigDecimal("volume"),
                resultSet.getBigDecimal("market_price_2"),
                resultSet.getBigDecimal("market_price_3"),
                resultSet.getBigDecimal("admitted_qoute"),
                resultSet.getBigDecimal("vmp_2_val_trd"),
                resultSet.getBigDecimal("market_price_3_trade_value"),
                resultSet.getBigDecimal("admitted_value"),
                resultSet.getBigDecimal("waval"));


    @Transactional
    public List<Security> findAllSecurityDataByDate(LocalDate date){
        return jdbcTemplate.query(SELECT_SECURITY_BY_DATE, securityRowMapper, date);
    }
    @Transactional
    public Security findOneSecurityByNameOnDate(LocalDate date, String name){
        int[] f = {1, 4, 6, 2};
        Arrays.sort(f);
        return jdbcTemplate.queryForObject(SELECT_ONE_SECURITY_BY_NAME_ON_DATE,securityRowMapper, date, name);
    }

    @Transactional
    public void insRow(Security security){
        jdbcTemplate.update(INSERT_SECURITY_INTO_DB, getParams(security));
    }

    private Object[] getParams(Security security){
        Object[] params = new Object[20];
        params[0] = security.getBoardId();
        params[1] = security.getTradeDate();
        params[2] = security.getShortName();
        params[3] = security.getSecId();
        params[4] = security.getNumTrades();
        params[5] = security.getValue();
        params[6] = security.getOpen();
        params[7] = security.getLow();
        params[8] = security.getHigh();
        params[9] = security.getLegalClosePrice();
        params[10] = security.getWaPrice();
        params[11] = security.getClose();
        params[12] = security.getVolume();
        params[13] = security.getMarketPrice2();
        params[14] = security.getMarketPrice3();
        params[15] = security.getAdmittedQoute();
        params[16] = security.getMp2ValTrd();
        params[17] = security.getMarketPrice3TradeValue();
        params[18] = security.getAdmittedValue();
        params[19] = security.getWaval();
        return params;
    }
}


