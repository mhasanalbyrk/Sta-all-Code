package com.obss.jss;

import com.obss.jss.service.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CalculatorApp implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorApp.class);
    @Qualifier("scientificCalculator")
    @Autowired
    private Calculator calculator1;

    @Qualifier("simpleCalculator")
    @Autowired
    private Calculator calculator2;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Scientific {} ",  calculator1.calculate(1, (long) 1.0));
        LOGGER.info("Simple {}", calculator2.calculate(1, (long) 1.0));
        System.out.println(calculator1.calculate(1, (long) 1.0));
        System.out.println(calculator2.calculate(1, (long) 1.00));

    }
}
