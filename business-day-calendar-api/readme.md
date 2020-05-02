**business-day-calendar-api** - REST-сервис для работы с производственным календарем в заданной стране.

В текущей версии реализована функциональность получения
- даты следующего рабочего дня 2017-2020 годаов с учетом праздников, выходных и предпраздничных выходных дней (GET):

        - /getNextWorkingDate?InDate=2017-03-07&Country=RUS
        - /getNextWorkingDate?InDate=2019-04-08&Country=RUS
        - /getNextWorkingDate?InDate=2020-05-08&Country=LUX
        
- даты предыдущего рабочего дня (GET):

        - /getPreviousWorkingDate?InDate=2017-05-26&Country=LUX
        - /getPreviousWorkingDate?InDate=2017-05-02&Country=RUS 
               
- признака является ли дата рабочим днём (GET):
 
        - /isDateWorkingDay?InDate=2017-05-09&Country=RUS

- количества рабочих дней между двумя датами (дата начала и окончания периода включаются в расчет) (GET):

        - /getWorkingDaysBetweenDates?BeginDate=2017-05-09&EndDate=2017-05-15&Country=RUS
для следующих стран:
- RUS
- LUX

Перечень с стран и дат, приходящихся на праздники, задаётся в *.properties файлах. Вы можете задать праздники на 2018 год.


1. Запуск из IDE `mvn spring-boot:run -Dspring.profiles.active=dev`
2. Запуск из коммандной строки `java -Dspring.profiles.active=dev -jar factorycalendar-X.X.jar`
3. Сборка `mvn package -Dspring.profiles.active=dev`


**ENGLISH**

**business-day-calendar-api** - REST-service for working with a production calendar in a specified country.

In the current version, the functionality for getting 
- next working day (GET):

        - /getNextWorkingDate?InDate=2017-03-07&Country=RUS
        - /getNextWorkingDate?InDate=2019-04-08&Country=RUS
        - /getNextWorkingDate?InDate=2020-05-08&Country=LUX
- previous working date (GET):

        - /getPreviousWorkingDate?InDate=2017-05-26&Country=LUX
        - /getPreviousWorkingDate?InDate=2017-05-02&Country=RUS 
        
- is date a working date in the Country (GET):

        - /isDateWorkingDay?InDate=2017-05-09&Country=RUS
        
- the number od days between two dates (begin and end dates are inclusive) (GET):
        - /getWorkingDaysBetweenDates?BeginDate=2017-05-09&EndDate=2017-05-15&Country=RUS
        
is set up for holidays in 2017 and for next countries:
- RUS
- LUX

The list of countries and dates of holidays is specified in * .properties files. You may set up holidays for 2018 year.


1. Running from the IDE `mvn spring-boot:run -Dspring.profiles.active=dev`
2. Run from command line `java -Dspring.profiles.active=dev -jar factorycalendar-X.X.jar`
3. Assembling to standalone jar - `mvn package -Dspring.profiles.active=dev`


**CHANGE LOG**

0.0.2
- Добавил поддержду календаря рабочих дней США