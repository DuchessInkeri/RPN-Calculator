package test;

import calculator.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CalculateTest {
    @Test
    void main() {
        String string = "-123";
        var calculate = Calculate.calculate(Tokenizer.tokenize(string));
        double answer = -123d;
        assertEquals(calculate, answer);
    }
}