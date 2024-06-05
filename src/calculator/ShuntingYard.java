package calculator;

import java.util.ArrayList;
import java.util.Stack;

public class ShuntingYard {
    private static int position(Token token) {
        return switch (token.value) {
            case "m", "p" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> 0;
        };
    }

    public static TokenCollection convert(ArrayList<Token> tokens) {
        TokenCollection result = new TokenCollection();
        Stack<Token> stack = new Stack<>();
        boolean afterParentheses = false;
        Token lastToken = tokens.getFirst();
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.isNumber()) {
                result.add(token);
            } else if (token.value.equals("(")) {
                stack.push(token);
            } else if (token.type == Token.Type.OPERATOR) {
                if (i == 0 && token.value.equals("-")) {
                    token.value = "m";
                } else if (i == 0 && token.value.equals("+")) {
                    token.value = "p";
                } else if (!stack.empty()) {
                    var lastOP = stack.peek();
                    if (!afterParentheses) {
                        if ((lastOP.type == Token.Type.OPERATOR || lastOP.value.equals("("))
                                && token.value.equals("-") && !lastOP.value.equals("m")
                                && !lastOP.value.equals("p") && !lastToken.isNumber()) {
                            token.value = "m";
                        }
                        if ((lastOP.type == Token.Type.OPERATOR || lastOP.value.equals("("))
                                && token.value.equals("+") && !lastOP.value.equals("m")
                                && !lastOP.value.equals("p") && !lastToken.isNumber()) {
                            token.value = "p";
                        }
                    }
                    while (lastOP.type == Token.Type.OPERATOR) {
                        if (
                            token.isLeftAssociativity() && position(token) <= position(lastOP)
                            || !token.isLeftAssociativity() && position(token) < position(lastOP)
                        ) {
                            result.add(stack.pop());
                            if (stack.empty()) {
                                break;
                            }
                            lastOP = stack.peek();
                        } else {
                            break;
                        }
                    }
                }
                stack.push(token);
                afterParentheses = false;
            } else if (token.value.equals(")")) {
                while (!stack.peek().value.equals("(")) {
                    result.add(stack.pop());
                }
                stack.pop();
                afterParentheses = true;
            }
            lastToken = token;
        }
        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return result;
    }
}