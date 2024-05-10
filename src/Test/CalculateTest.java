package Test;

import Calculator.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CalculateTest {
    @Test
    void main() {
        String string = "-123";
        var tokens = Tokenizer.tokenize(string);
        var calculate = Calculate.calculate(tokens);
        double answer = -123d;
        assertEquals(calculate.getLast().doubleValue(), answer);
    }
}