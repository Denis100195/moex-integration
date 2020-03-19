package com.stock.analysis.moex.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Security {
    private String boardId;
    private LocalDate tradeDate;
    private String shortName;
    private String secId;
    private double numTrades; //TODO вместо double лучше использовать BigDecimal. Короче никогда double не используй
    // вот две статьи с примерами, почему это плохо
    // https://computingat40s.wordpress.com/java-float-and-double-primitive-types-are-evil-dont-use-them/
    // https://stackoverflow.com/questions/3730019/why-not-use-double-or-float-to-represent-currency
    private double value;
    private double open;
    private double low;
    private double hight; // TODO high
    private double legalClosePrice;
    private double waPrice;
    private double close;
    private double volume;
    private double marketPrice2, MarketPrice3;
    private double admittedQoute;
    private double mp2ValTrd;
    private double marketPrice3TradeValue;
    private double admittedValue;
    private double waval;

    // TODO тут геттеры и сеттеры не нужны, lombok ведь используем
    // TODO почитай эту статью https://habr.com/ru/post/345520/

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public double getNumTrades() {
        return numTrades;
    }

    public void setNumTrades(double numTrades) {
        this.numTrades = numTrades;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHight() {
        return hight;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public double getLegalClosePrice() {
        return legalClosePrice;
    }

    public void setLegalClosePrice(double legalClosePrice) {
        this.legalClosePrice = legalClosePrice;
    }

    public double getWaPrice() {
        return waPrice;
    }

    public void setWaPrice(double waPrice) {
        this.waPrice = waPrice;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getMarketPrice2() {
        return marketPrice2;
    }

    public void setMarketPrice2(double marketPrice2) {
        this.marketPrice2 = marketPrice2;
    }

    public double GetMarketPrice3() {
        return MarketPrice3;
    }

    public void setMarketPrice3(double MarketPrice3) {
        this.MarketPrice3 = MarketPrice3;
    }

    public double getAdmittedQoute() {
        return admittedQoute;
    }

    public void setAdmittedQoute(double admittedQoute) {
        this.admittedQoute = admittedQoute;
    }

    public double getMp2ValTrd() {
        return mp2ValTrd;
    }

    public void setMp2ValTrd(double mp2ValTrd) {
        this.mp2ValTrd = mp2ValTrd;
    }

    public double getMarketPrice3TradeValue() {
        return marketPrice3TradeValue;
    }

    public void setMarketPrice3TradeValue(double marketPrice3TradeValue) {
        this.marketPrice3TradeValue = marketPrice3TradeValue;
    }

    public double getAdmittedValue() {
        return admittedValue;
    }

    public void setAdmittedValue(double admittedValue) {
        this.admittedValue = admittedValue;
    }

    public double getWaval() {
        return waval;
    }

    public void setWaval(double waval) {
        this.waval = waval;
    }
}
