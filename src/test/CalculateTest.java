package test;

import org.junit.jupiter.api.Test;

import static calculator.Calculate.calculate;
import static calculator.Tokenizer.tokenize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateTest {
    @Test
    void main() {
        String string = "-123";
        var calculate = calculate(tokenize(string));
        double answer = -123d;
        assertEquals(calculate, answer);
    }
}