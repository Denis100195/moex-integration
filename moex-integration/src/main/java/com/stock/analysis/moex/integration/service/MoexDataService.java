package com.stock.analysis.moex.integration.service;

import com.stock.analysis.moex.integration.client.BusinessCalendarClient;
import com.stock.analysis.moex.integration.dto.Security;
import com.stock.analysis.moex.integration.repository.SecurityRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MoexDataService {

    final static String RUSSIA_CODE = "RUS"; //todo вынести в класс констант
    private BusinessCalendarClient businessCalendarClient;
    private SecurityRepository securityRepository;
    @Value("${moex.service.url}")
    private String moexServiceUrl;

    @Autowired
    public MoexDataService(SecurityRepository securityRepository){
        this.securityRepository = securityRepository;
    }

    public List<Security> getSecuritiesOnDateFromMoex(LocalDate date) throws Exception {
        InputStream stream =
                URI.create(moexServiceUrl + date.toString())
                        .toURL().openStream();
        List<Security> securityList = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(stream);

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT && "row".equals(reader.getLocalName())) {

                Security currSec = Security.builder()
                        .boardId(reader.getAttributeValue(0))
                        .tradeDate(LocalDate.parse(reader.getAttributeValue(1)))
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
            if (event == XMLStreamConstants.END_ELEMENT && "rows".equals(reader.getLocalName())) {
                return securityList;
            }
        }

        return securityList;
    }

    // 1. создать метод который на определенную дату сохраняет данные в базу на определенный день, предусм обработку ошибок

    @Transactional
    @Scheduled(cron = "${cron}")
    public void saveSecuritiesOnPreviousWorkingDate() {
        try {
            log.info("Метод");
            List<Security> sl = getSecuritiesOnDateFromMoex(LocalDate.now().minusDays(1));
            for (int i = 0; i < sl.size(); i++) {
                securityRepository.insRow(sl.get(i));
            }
        } catch (Exception e) {
            log.error("Ошибка в методе putSecurity {}", ExceptionUtils.getStackTrace(e));
        }

    }
    //1. Создать метод, который сохраняет данные с сайта в базу на дату, переданную в параметры
    public void saveSecuritiesOnDate(LocalDate date) throws Exception{
        List<Security> secOnDate = getSecuritiesOnDateFromMoex(date);
        for (int i = 0; i < secOnDate.size(); i++) {
            securityRepository.insRow(secOnDate.get(i));
        }
    }
    @Scheduled(cron = "${cron}")
    public void addSecOnPrevWorkDay() throws Exception{
        LocalDate workDay = businessCalendarClient.getPreviousWorkingDate(LocalDate.now(), RUSSIA_CODE);
        saveSecuritiesOnDate(workDay);
    }

    // 2. @Scheduled вторник-суббота

    // 3. метод котор возращает даннные из базы на определенную дату
    public List<Security> getSecurityDataOnDate(LocalDate date) {
        return securityRepository.findAllSecurityDataByDate(date);
    }

    public List<Security> getTopSecurity(final LocalDate date){
        return getSecurityDataOnDate(date)
                .stream()
                .filter(security -> security.getHigh().compareTo(BigDecimal.valueOf(200)) > 0)
                .sorted(Comparator.comparing(Security::getHigh))
                .limit(10)
                .collect(Collectors.toList());


    }
    // на его основе вывести топ 10 бумаг с максимальным объемом, у которых цена (high) больше 200. с использ стримов
    // поднять первое прилож и сделать в него rest запрос. созд директ client. созд класс.
    //
    // вынести URL в проперти. @value

    // 4. метод который возвращает данные по одной бумаге
    public Security getOneSecurityByNameOnDate(LocalDate date, String shName) {
        Security sec = securityRepository.findOneSecurityByNameOnDate(date, shName);
        return sec;
    }



}

//установить postman для запросов
//soap UI

//контроллер
//поднять локально бизнес календарь. через постман сделать к нему запросы.
//вынести cron в проперти отдельной переменной