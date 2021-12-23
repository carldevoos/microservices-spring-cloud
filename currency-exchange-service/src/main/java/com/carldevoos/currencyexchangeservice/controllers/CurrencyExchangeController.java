package com.carldevoos.currencyexchangeservice.controllers;

import com.carldevoos.currencyexchangeservice.models.CurrencyExchange;
import com.carldevoos.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieverExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {
        /*CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(4.1));
         */
        logger.info("retrieverExchangeValue, from {} to {}", from, to);

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for ".concat(from).concat(" to ").concat(to));
        }

        String port = environment.getProperty("local.server.port");

        // Change for kubernetes
        String host = environment.getProperty("HOSTNAME");
        String version = "v11";

        currencyExchange.setEnvironment(String.format("%s %s %s",port, version, host));

        return currencyExchange;
    }
}
