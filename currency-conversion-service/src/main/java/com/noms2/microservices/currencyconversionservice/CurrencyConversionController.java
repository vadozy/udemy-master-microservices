package com.noms2.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables );
		
		CurrencyConversionBean r = responseEntity.getBody();
		
		CurrencyConversionBean convertedResponse = new CurrencyConversionBean(r.getId(), from, to, r.getConversionMultiple(), quantity, quantity.multiply(r.getConversionMultiple()), r.getPort());
		
		return convertedResponse;
		
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		CurrencyConversionBean r = proxy.retrieveExchangeValue(from, to);
		
		CurrencyConversionBean convertedResponse = new CurrencyConversionBean(r.getId(), from, to, r.getConversionMultiple(), quantity, quantity.multiply(r.getConversionMultiple()), r.getPort());
		
		return convertedResponse;
		
	}

}
