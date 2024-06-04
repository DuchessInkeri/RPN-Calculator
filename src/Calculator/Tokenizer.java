package Calculator;

public class Tokenizer {
    enum State {
        START, //
        INT, //Запись INT чисел в буфер
        FLOAT, //Запись FLOAT чисел в буфер
    }

    public static TokenCollection tokenize(String string) {
        TokenCollection tokens = new TokenCollection();
        State state = State.START;
        StringBuilder buffer = new StringBuilder();
        for (int position = 0; position < string.length(); position++) {
            char symbol = string.charAt(position);
            if (symbol == ' ' || symbol == '\t') {
                switch (state) {
                    case START: //пропуск пробела
                        break;
                    case INT:
                        tokens.add(new Token(Token.Type.INT, buffer.toString()));
                        buffer.setLength(0);
                        state = State.START;
                        break;
                    case FLOAT:
                        tokens.add(new Token(Token.Type.FLOAT, buffer.toString()));
                        buffer.setLength(0);
                        state = State.START;
                        break;
                    default:
                        throw new Error("Unrecognized symbol: '%s'".formatted(symbol));
                }
            } else if (Token.isOperator(symbol)) {
                switch (state) {
                    case START:
                        tokens.add(new Token(Token.Type.OPERATOR, String.valueOf(symbol)));
                        break;
                    case INT:
                        tokens.add(new Token(Token.Type.INT, buffer.toString()));
                        buffer.setLength(0);
                        state = State.START;
                        tokens.add(new Token(Token.Type.OPERATOR, String.valueOf(symbol)));
                        break;
                    case FLOAT:
                        tokens.add(new Token(Token.Type.FLOAT, buffer.toString()));
                        buffer.setLength(0);
                        state = State.START;
                        tokens.add(new Token(Token.Type.OPERATOR, String.valueOf(symbol)));
                        break;
                    default:
                        throw new Error("Unrecognized symbol: '%s'".formatted(symbol));
                }
            } else if (Token.isDigit(symbol)) {
                switch (state) {
                    case START:
                        buffer.append(symbol);
                        state = State.INT;
                        break;
                    case INT, FLOAT:
                        buffer.append(symbol);
                        break;
                    default:
                        throw new Error("Unrecognized symbol: '%s'".formatted(symbol));
                }
            } else if (symbol == '.') {
                switch (state) {
                    case START, INT:
                        buffer.append(symbol);
                        state = State.FLOAT;
                        break;
                    case FLOAT:
                        break; // пропуск точки
                    default:
                        throw new Error("Unrecognized symbol: '%s'".formatted(symbol));
                }
            } else if (symbol == '(' || symbol == ')') {
                Token.Type symbolType = symbol == '(' ? Token.Type.L_BRACKET : Token.Type.R_BRACKET;
                switch (state) {
                    case START:
                        tokens.add(new Token(symbolType, String.valueOf(symbol)));
                        break;
                    case INT:
                        tokens.add(new Token(Token.Type.INT, buffer.toString()));
                        buffer.setLength(0);
                        state = State.START;
                        tokens.add(new Token(symbolType, String.valueOf(symbol)));
                        break;
                    case FLOAT:
                        tokens.add(new Token(Token.Type.FLOAT, buffer.toString()));
                        buffer.setLength(0);
                        state = State.START;
                        tokens.add(new Token(symbolType, String.valueOf(symbol)));
                        break;
                    default:
                        throw new Error("Unrecognized symbol: '%s'".formatted(symbol));
                }
            }
        }
        if (!buffer.isEmpty()) {
            tokens.add(new Token(state == State.FLOAT ? Token.Type.FLOAT : Token.Type.INT, buffer.toString()));
        }
        return tokens;
    }
}



