package com.test;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @Name(value = "should return the sum of the string numbers to integer")
    void toSum() {

        Calculator calc = new Calculator();
        int sum = calc.toSum("1+1+3");
        assertEquals(5, sum);
    }
}