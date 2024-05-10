package Calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Calculate {
    public static Stack<Double> calculate (ArrayList<Token> tokens) {
        TokenCollection result = RPN.convert(tokens);
        Stack<Double> answer = new Stack<>();
        for (Token token : result) {
            if (token.isNumber()) {
                answer.push(Double.parseDouble(token.value));
            } else if (token.type.equals(Type.OPERATOR)) {
                if (token.isUnary()) {
                    double opU = answer.pop();
                    switch (token.value) {
                        case "p":
                            answer.push(+opU);
                            break;

                        case "m":
                            answer.push(-opU);
                            break;
                    }
                } else {
                    double opL = answer.pop();
                    double opR = answer.pop();
                    switch (token.value) {
                        case "+":
                            answer.push(opL + opR);
                            break;

                        case "-":
                            answer.push(opR - opL);
                            break;

                        case "*":
                            answer.push(opR * opL);
                            break;

                        case "/":
                            answer.push(opR / opL);
                            break;
                    }
                }
            }
        }
        return answer;
    }
}