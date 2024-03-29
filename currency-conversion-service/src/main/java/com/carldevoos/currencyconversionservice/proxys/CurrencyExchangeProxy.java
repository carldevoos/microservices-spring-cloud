package com.carldevoos.currencyconversionservice.proxys;

import com.carldevoos.currencyconversionservice.models.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieverExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}