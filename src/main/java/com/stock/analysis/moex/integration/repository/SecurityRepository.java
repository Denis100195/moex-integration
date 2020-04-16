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
import java.util.Date;
import java.util.List;

@Repository
public class SecurityRepository {
    private final JdbcTemplate jdbcTemplate;


    public SecurityRepository(final @Qualifier("hikariDS") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private static final String SELECT_FROM_DB = "select * from security";
    private static final String INSERT_INTO_DB = "INSERT INTO security values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private RowMapper<Security> securityRowMapper = (resultSet, i) -> {
        return new Security(
                resultSet.getString("board_id"),
                resultSet.getDate("trade_date"),
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
    };

    @Transactional
    public List<Security> findAll(){
        return jdbcTemplate.query(SELECT_FROM_DB, securityRowMapper);
    }

//    @Transactional
//    public List<String> getRows(){
        //return jdbcTemplate.query(SELECT_FROM_DB)


//                new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet resultSet, int i) throws SQLException {
//                resultSet.getString("board_id");
//                resultSet.getDate("trade_date");
//                resultSet.getString("short_name");
//                resultSet.getString("sec_id");
//                resultSet.getBigDecimal("num_trades");
//                resultSet.getBigDecimal("value");
//                resultSet.getBigDecimal("open");
//                resultSet.getBigDecimal("low");
//                resultSet.getBigDecimal("high");
//                resultSet.getBigDecimal("legal_close_price");
//                resultSet.getBigDecimal("wa_price");
//                resultSet.getBigDecimal("close");
//                resultSet.getBigDecimal("volume");
//                resultSet.getBigDecimal("market_price_2");
//                resultSet.getBigDecimal("market_price_3");
//                resultSet.getBigDecimal("admitted_qoute");
//                resultSet.getBigDecimal("vmp_2_val_trd");
//                resultSet.getBigDecimal("market_price_3_trade_value");
//                resultSet.getBigDecimal("admitted_value");
//                resultSet.getBigDecimal("waval");
//                resultSet.getBigDecimal("market_price");
//                resultSet.getBigDecimal("market_price");
//
//                return ;
//            }
//            });

//    }

    @Transactional
    public void insRow(String boardId, LocalDate tradeDate, String shortName, String secId,
                       BigDecimal numTrades, BigDecimal value, BigDecimal open, BigDecimal low,
                       BigDecimal high, BigDecimal legalClosePrice, BigDecimal waPrice,
                       BigDecimal close, BigDecimal volume, BigDecimal marketPrice2,
                       BigDecimal marketPrice3, BigDecimal admittedQoute, BigDecimal mp2ValTrd,
                       BigDecimal marketPrice3TradeValue, BigDecimal admittedValue, BigDecimal waval){
        jdbcTemplate.update(INSERT_INTO_DB, getParams(boardId, tradeDate, shortName, secId,
                numTrades, value, open, low, high, legalClosePrice, waPrice, close, volume,
                marketPrice2, marketPrice3, admittedQoute, mp2ValTrd, marketPrice3TradeValue,
                admittedValue, waval));
    }

    private Object[] getParams(String boardId,
            LocalDate tradeDate, String shortName, String secId,
            BigDecimal numTrades, BigDecimal value, BigDecimal open,
            BigDecimal low, BigDecimal high, BigDecimal legalClosePrice,
            BigDecimal waPrice, BigDecimal close, BigDecimal volume,
            BigDecimal marketPrice2, BigDecimal marketPrice3, BigDecimal admittedQoute,
            BigDecimal mp2ValTrd, BigDecimal marketPrice3TradeValue,
            BigDecimal admittedValue, BigDecimal waval){
        Object[] params = new Object[20];
        params[0] = boardId;
        params[1] = tradeDate;
        params[2] = shortName;
        params[3] = secId;
        params[4] = numTrades;
        params[5] = value;
        params[6] = open;
        params[7] = low;
        params[8] = high;
        params[9] = legalClosePrice;
        params[10] = waPrice;
        params[11] = close;
        params[12] = volume;
        params[13] = marketPrice2;
        params[14] = marketPrice3;
        params[15] = admittedQoute;
        params[16] = mp2ValTrd;
        params[17] = marketPrice3TradeValue;
        params[18] = admittedValue;
        params[19] = waval;

        return params;
    }


}

/*прокси в спринге что это
* @Transactional что это
*
* сделать инсерт в нормальную таблицу для секьюрити (создать ее)
* создать тест который делает запись в таблицу
* создать объект секьюрити в тесте (заполнить своими значениями) и сохранить его в бд
* 
*
* */

