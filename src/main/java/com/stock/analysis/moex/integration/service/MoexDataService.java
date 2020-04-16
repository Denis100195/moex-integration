package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.dto.Security;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MoexDataService {
    public List<Security> parseDoc(LocalDate date) throws Exception {
        InputStream stream =
                URI.create("http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.xml?date=" + date.toString())
                        .toURL().openStream();
        List<Security> securityList = new ArrayList<>();


        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(stream);

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT && "row".equals(reader.getLocalName())) {

                Security currSec = Security.builder()
                        .boardId(reader.getAttributeValue(0))
                        .tradeDate(new Date(reader.getAttributeValue(1)))
                        .shortName(reader.getAttributeValue(2))
                        .secId(reader.getAttributeValue(3))
                        .numTrades(NumberUtils.isCreatable(reader.getAttributeValue(4)) ? new BigDecimal(reader.getAttributeValue(4)) : null)
                        .value(NumberUtils.isCreatable(reader.getAttributeValue(5)) ? new BigDecimal(reader.getAttributeValue(5)) : null)
                        .open(NumberUtils.isCreatable(reader.getAttributeValue(6)) ? new BigDecimal(reader.getAttributeValue(6)) : null)
                        .low(NumberUtils.isCreatable(reader.getAttributeValue(7)) ? new BigDecimal(reader.getAttributeValue(7)) : null)
                        .high(NumberUtils.isCreatable(reader.getAttributeValue(8)) ? new BigDecimal(reader.getAttributeValue(8)) : null)
                        .legalClosePrice(NumberUtils.isCreatable(reader.getAttributeValue(9)) ? new BigDecimal(reader.getAttributeValue(9)) : null)
                        .waPrice(NumberUtils.isCreatable(reader.getAttributeValue(10)) ? new BigDecimal(reader.getAttributeValue(10)) : null)
                        .close(NumberUtils.isCreatable(reader.getAttributeValue(11)) ? new BigDecimal(reader.getAttributeValue(11)) : null)
                        .volume(NumberUtils.isCreatable(reader.getAttributeValue(12)) ? new BigDecimal(reader.getAttributeValue(12)) : null)
                        .marketPrice2(NumberUtils.isCreatable(reader.getAttributeValue(13)) ? new BigDecimal(reader.getAttributeValue(13)) : null)
                        .marketPrice3(NumberUtils.isCreatable(reader.getAttributeValue(14)) ? new BigDecimal(reader.getAttributeValue(14)) : null)
                        .admittedQoute(NumberUtils.isCreatable(reader.getAttributeValue(15)) ? new BigDecimal(reader.getAttributeValue(15)) : null)
                        .mp2ValTrd(NumberUtils.isCreatable(reader.getAttributeValue(16)) ? new BigDecimal(reader.getAttributeValue(16)) : null)
                        .marketPrice3TradeValue(NumberUtils.isCreatable(reader.getAttributeValue(17)) ? new BigDecimal(reader.getAttributeValue(17)) : null)
                        .admittedValue(NumberUtils.isCreatable(reader.getAttributeValue(18)) ? new BigDecimal(reader.getAttributeValue(18)) : null)
                        .waval(NumberUtils.isCreatable(reader.getAttributeValue(19)) ? new BigDecimal(reader.getAttributeValue(19)) : null)
                        .build();

                securityList.add(currSec);
            }
            if (event == XMLStreamConstants.END_ELEMENT && "rows".equals(reader.getLocalName())){
                return securityList;
            }
        }
        securityList.forEach(System.out::println);
        return securityList;
    }


}
