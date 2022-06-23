package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    CalculatorTest() {
    }

    @Test
    void calculate() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(5.0, calculator.calculate("2+3"));
        Assertions.assertEquals(6.0, calculator.calculate("2*3"));
        Assertions.assertEquals(2.0, calculator.calculate("4/2"));
        Assertions.assertEquals(0.0, calculator.calculate("1-1"));
        Assertions.assertEquals(0.0, calculator.calculate("1 - 1"));
        Assertions.assertEquals(9.0, calculator.calculate("3(2+1)"));
        Assertions.assertEquals(24.0, calculator.calculate("(5+1)4"));
        Assertions.assertEquals(2.0, calculator.calculate("(7+1)/4"));
        Assertions.assertEquals(10.0, calculator.calculate("(1+1)(2+3)"));
    }
}