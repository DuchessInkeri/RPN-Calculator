package Test;

import org.junit.jupiter.api.Test;

import static Calculator.Calculate.calculate;
import static Calculator.Tokenizer.tokenize;
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