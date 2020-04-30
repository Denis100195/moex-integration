**factorycalendar** - REST-сервис для работы с производственным календарем в заданной стране.

В текущей версии реализована функциональность получения
- даты следующего рабочего дня 2017 года в с учетом праздников, выходных и предпраздничных выходных дней (GET):

        - http://localhost:8080/getNextWorkingDate?InDate=31.12.2016&Country=Russia
        - http://localhost:8080/getNextWorkingDate?InDate=07.03.2017&Country=Russia
        - http://localhost:8080/getNextWorkingDate?InDate=24.05.2017&Country=Luxembourg
        
- даты предыдущего рабочего дня (GET):

        - http://localhost:8080/getPreviousWorkingDate?InDate=26.05.2017&Country=Luxembourg
        - http://localhost:8080/getPreviousWorkingDate?InDate=02.05.2017&Country=Russia 
               
- признака является ли дата рабочим днём (GET):
 
        - http://localhost:8080/isDateWorkingDay?InDate=09.05.2017&Country=Russia

- количества рабочих дней между двумя датами (дата начала и окончания периода включаются в расчет) (GET):

        - http://localhost:8080/getWorkingDaysBetweenDates?BeginDate=31.12.2016&EndDate=15.01.2017&Country=Russia
для следующих стран:
- Russia
- Luxembourg

Перечень с стран и дат, приходящихся на праздники, задаётся в *.properties файлах. Вы можете задать праздники на 2018 год.


1. Запуск из IDE `mvn spring-boot:run -Dspring.profiles.active=dev`
2. Запуск из коммандной строки `java -Dspring.profiles.active=dev -jar factorycalendar-X.X.jar`
3. Сборка `mvn package -Dspring.profiles.active=dev`


**ENGLISH**

**factorycalendar** - REST-service for working with a production calendar in a specified country.

In the current version, the functionality for getting 
- next working day (GET):

        - http://localhost:8080/getNextWorkingDate?InDate=31.12.2016&Country=Russia
        - http://localhost:8080/getNextWorkingDate?InDate=03.07.2017&Country=Russia
        - http://localhost:8080/getNextWorkingDate?InDate=24.05.2017&Country=Luxembourg
- previous working date (GET):

        - http://localhost:8080/getPreviousWorkingDate?InDate=26.05.2017&Country=Luxembourg
        - http://localhost:8080/getPreviousWorkingDate?InDate=02.05.2017&Country=Russia
- is date a working date in the Country (GET):

        - http://localhost:8080/isDateWorkingDay?InDate=09.05.2017&Country=Russia
- the number od days between two dates (begin and end dates are inclusive) (GET):

        - http://localhost:8080/getWorkingDaysBetweenDates?BeginDate=31.12.2016&EndDate=15.01.2017&Country=Russia
is set up for holidays in 2017 and for next countries:
- Russia
- Luxembourg

The list of countries and dates of holidays is specified in * .properties files. You may set up holidays for 2018 year.


1. Running from the IDE `mvn spring-boot:run -Dspring.profiles.active=dev`
2. Run from command line `java -Dspring.profiles.active=dev -jar factorycalendar-X.X.jar`
3. Assembling to standalone jar - `mvn package -Dspring.profiles.active=dev`