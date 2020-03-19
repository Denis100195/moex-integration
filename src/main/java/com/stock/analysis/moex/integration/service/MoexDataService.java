package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.dto.Security;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class MoexDataService {

    public List<Security> parseDoc(LocalDate date) throws Exception {
        InputStream stream =
                URI.create("http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.xml")
                .toURL().openStream();
        List<Security> securityList = null;
        Security currSec = null;
        String tagContent = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(stream);

        while (reader.hasNext()){
            int event = reader.next();

            switch (event){
                case XMLStreamConstants.START_ELEMENT:
                    if ("rows".equals(reader.getLocalName())){
                        securityList = new ArrayList<>();
                    }
                    if ("row".equals(reader.getLocalName())){
                        currSec = new Security();
                        currSec.setBoardId(reader.getAttributeValue(0));
                        currSec.setTradeDate(LocalDate.parse(reader.getAttributeValue(1)));
                        currSec.setShortName(reader.getAttributeValue(2));
                        currSec.setSecId(reader.getAttributeValue(3));
                        currSec.setNumTrades(Double.parseDouble(reader.getAttributeValue(4)));
                        currSec.setValue(Double.parseDouble(reader.getAttributeValue(5)));
                        currSec.setOpen(Double.parseDouble(reader.getAttributeValue(6)));
                        currSec.setLow(Double.parseDouble(reader.getAttributeValue(7)));
                        currSec.setHight(Double.parseDouble(reader.getAttributeValue(8)));
                        currSec.setLegalClosePrice(Double.parseDouble(reader.getAttributeValue(9)));
                        currSec.setWaPrice(Double.parseDouble(reader.getAttributeValue(10)));
                        currSec.setClose(Double.parseDouble(reader.getAttributeValue(11)));
                        currSec.setVolume(Double.parseDouble(reader.getAttributeValue(12)));
                        currSec.setMarketPrice2(Double.parseDouble(reader.getAttributeValue(13)));
                        currSec.setMarketPrice3(Double.parseDouble(reader.getAttributeValue(14)));
                        currSec.setAdmittedQoute(Double.parseDouble(reader.getAttributeValue(15)));
                        currSec.setMp2ValTrd(Double.parseDouble(reader.getAttributeValue(16)));
                        currSec.setMarketPrice3TradeValue(Double.parseDouble(reader.getAttributeValue(17)));
                        currSec.setAdmittedValue(Double.parseDouble(reader.getAttributeValue(18)));
                        currSec.setWaval(Double.parseDouble(reader.getAttributeValue(19)));
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    securityList = new ArrayList<>();
                    break;
            }
        }
        for (Security security: securityList){
            System.out.println(security);
        }
        return securityList;
    }


}
