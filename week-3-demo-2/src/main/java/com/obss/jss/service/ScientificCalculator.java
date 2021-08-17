package com.obss.jss.service;

import com.obss.jss.AcademicCalcUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScientificCalculator implements Calculator {
    @Autowired
    private AcademicCalcUtility util;

    @Override
    public long calculate(long number1, long number2) {
        return number1 * number2;
    }
}
