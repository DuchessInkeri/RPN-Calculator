package test;

import calculator.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class ShuntingYardTest {
    @Test
    void main() {
        var input = new ArrayList<>(
                //-(-67)/(30*-3/-(33))/(4/((((24/-44-38)))))
                Arrays.asList(
                        new Token(Token.Type.OPERATOR, "-"),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.OPERATOR, "-"),
                        new Token(Token.Type.INT, "67"),
                        new Token(Token.Type.R_BRACKET, ")"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.INT, "30"),
                        new Token(Token.Type.OPERATOR, "*"),
                        new Token(Token.Type.OPERATOR, "-"),
                        new Token(Token.Type.INT, "3"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.OPERATOR, "-"),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.INT, "33"),
                        new Token(Token.Type.R_BRACKET, ")"),
                        new Token(Token.Type.R_BRACKET, ")"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.INT, "4"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.L_BRACKET, "("),
                        new Token(Token.Type.INT, "24"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.OPERATOR, "-"),
                        new Token(Token.Type.INT, "44"),
                        new Token(Token.Type.OPERATOR, "-"),
                        new Token(Token.Type.INT, "38"),
                        new Token(Token.Type.R_BRACKET, ")"),
                        new Token(Token.Type.R_BRACKET, ")"),
                        new Token(Token.Type.R_BRACKET, ")"),
                        new Token(Token.Type.R_BRACKET, ")"),
                        new Token(Token.Type.R_BRACKET, ")")
                )
        );

        var expected = new ArrayList<>(
                //['67', 'm', 'm', '30', '3', 'm', '*', '33', 'm', '/', '/', '4', '24', '44', 'm', '/', '38', '-', '/', '/']
                Arrays.asList(
                        new Token(Token.Type.INT, "67"),
                        new Token(Token.Type.OPERATOR, "m"),
                        new Token(Token.Type.OPERATOR, "m"),
                        new Token(Token.Type.INT, "30"),
                        new Token(Token.Type.INT, "3"),
                        new Token(Token.Type.OPERATOR, "m"),
                        new Token(Token.Type.OPERATOR, "*"),
                        new Token(Token.Type.INT, "33"),
                        new Token(Token.Type.OPERATOR, "m"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.INT, "4"),
                        new Token(Token.Type.INT, "24"),
                        new Token(Token.Type.INT, "44"),
                        new Token(Token.Type.OPERATOR, "m"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.INT, "38"),
                        new Token(Token.Type.OPERATOR, "-"),
                        new Token(Token.Type.OPERATOR, "/"),
                        new Token(Token.Type.OPERATOR, "/")
                )
        );
        var result = ShuntingYard.convert(input);
        Assertions.assertEquals(result.toArray(), expected.toArray());
    }
}

