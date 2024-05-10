package Test;

import Calculator.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

class CalculateTest {
    @Test
    void main() {
        String string = "-123";
        var tokens = Tokenizer.tokenize(string);
        var result = RPN.convert(tokens);
        var calculate = Calculate.calculate(tokens);
        double answer = -123d;
        assertEquals(calculate.getLast().doubleValue(), answer);
    }
}