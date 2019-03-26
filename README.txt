
Config Server URL's

http://localhost:8888/limits-service/default
http://localhost:8888/limits-service/dev
http://localhost:8888/limits-service/qa


Limit server URL's

http://localhost:8080/limits
http://localhost:8082/limits

http://localhost:8080/fault-tolerance-example

Config update one instance:
POST http://localhost:8080/actuator/refresh

Config update all instances:
POST http://localhost:8080/actuator/bus-refresh

Currency Exchannge

http://localhost:8000/currency-exchange/from/USD/to/EUR
http://localhost:8000/currency-exchange/from/USD/to/AUD

http://localhost:8001/currency-exchange/from/USD/to/AUD

http://localhost:8000/h2-console/
org.h2.Driver
jdbc:h2:mem:testdb
sa


Eureka

http://localhost:8761/


Currency Conversion

http://localhost:8100/currency-converter/from/USD/to/AUD/quantity/250
http://localhost:8101/currency-converter/from/USD/to/AUD/quantity/250

-- start zuul gateway before hitting the next url  ???
http://localhost:8100/currency-converter-feign/from/AUD/to/USD/quantity/250


zuul

http://localhost:8765/{application-name}/{uri}
http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/AUD

-- 2 times the logging filter is executed
http://localhost:8765/currency-conversion-service/currency-converter-feign/from/AUD/to/USD/quantity/250

-- Talk about Sleuth
