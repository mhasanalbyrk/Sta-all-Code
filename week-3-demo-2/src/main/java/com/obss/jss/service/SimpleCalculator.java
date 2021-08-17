package com.obss.jss.service;

import com.obss.jss.PrimarySchoolCalcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleCalculator implements Calculator {

    @Autowired
    private PrimarySchoolCalcUtil util;

    @Override
    public long calculate(long number1, long number2) {
        return number1 + number2;
    }
}
