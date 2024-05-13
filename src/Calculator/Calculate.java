package calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Calculate {
    public static double calculate(ArrayList<Token> tokens) {
        TokenCollection result = ShuntingYard.convert(tokens);
        Stack<Double> stack = new Stack<>();
        for (Token token : result) {
            if (token.isNumber()) {
                stack.push(Double.parseDouble(token.value));
            } else if (token.type == Token.Type.OPERATOR) {
                if (token.isUnary()) {
                    double opU = stack.pop();
                    switch (token.value) {
                        case "p":
                            stack.push(+opU);
                            break;

                        case "m":
                            stack.push(-opU);
                            break;

                        default:
                            throw new Error("Unrecognized operator '%s'".formatted(token.value));
                    }
                } else {
                    double opL = stack.pop();
                    double opR = stack.pop();
                    switch (token.value) {
                        case "+":
                            stack.push(opL + opR);
                            break;

                        case "-":
                            stack.push(opR - opL);
                            break;

                        case "*":
                            stack.push(opR * opL);
                            break;

                        case "/":
                            stack.push(opR / opL);
                            break;

                        default:
                            throw new Error("Unrecognized operator '%s'".formatted(token.value));

                    }
                }
            }
        }
        return stack.pop();
    }
}