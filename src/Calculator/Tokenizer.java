package Calculator;

public class Tokenizer {
    enum State {
        START, //
        INT, //Запись INT чисел в буфер
        FLOAT,//Запись FLOAT чисел в буфер
    }

    public static TokenCollection tokenize(String string) {
        TokenCollection tokens = new TokenCollection();
        State state = State.START;
        StringBuilder buffer = new StringBuilder();
        for (int position = 0; position < string.length(); position++)
        {
            char symbol = string.charAt(position);
            if (symbol == ' ' || symbol == '\t') {
                switch(state){
                    case START: //пропуск пробела
                        break;
                    case INT:
                        tokens.add(new Token(Type.INT, buffer.toString()));
                        buffer = new StringBuilder();
                        state = State.START;
                        break;
                    case FLOAT:
                        tokens.add(new Token(Type.FLOAT, buffer.toString()));
                        buffer = new StringBuilder();
                        state = State.START;
                        break;
                }
            }
            else if (Token.isOperator(symbol)) {
                switch(state){
                    case START:
                        tokens.add(new Token(Type.OPERATOR, String.valueOf(symbol)));
                        break;
                    case INT:
                        tokens.add(new Token(Type.INT, buffer.toString()));
                        buffer = new StringBuilder();
                        state = State.START;
                        tokens.add(new Token(Type.OPERATOR, String.valueOf(symbol)));
                        break;
                    case FLOAT:
                        tokens.add(new Token(Type.FLOAT, buffer.toString()));
                        buffer = new StringBuilder();
                        state = State.START;
                        tokens.add(new Token(Type.OPERATOR, String.valueOf(symbol)));
                        break;
                }
            }
            else if (Token.isDigit(symbol)) {
                switch (state) {
                    case START:
                        buffer.append(symbol);
                        state = State.INT;
                        break;
                    case INT:
                        buffer.append(symbol);
                        break;
                    case FLOAT:
                        buffer.append(symbol);
                        break;
                }
            }
            else if (symbol == '.') {
                switch (state) {
                    case START:
                        buffer.append(symbol);
                        state = State.FLOAT;
                        break;
                    case INT:
                        buffer.append(symbol);
                        state = State.FLOAT;
                        break;
                    case FLOAT:
                        break;// пропуск точки
                }
            }
            else if (symbol == '(' || symbol == ')') {
                Type symbolType = symbol == '(' ?  Type.L_BRACKET : Type.R_BRACKET;
                switch (state) {
                    case START:
                        tokens.add(new Token(symbolType, String.valueOf(symbol)));
                        break;
                    case INT:
                        tokens.add(new Token(Type.INT, buffer.toString()));
                        buffer = new StringBuilder();
                        state = State.START;
                        tokens.add(new Token(symbolType, String.valueOf(symbol)));
                        break;
                    case FLOAT:
                        tokens.add(new Token(Type.FLOAT, buffer.toString()));
                        buffer = new StringBuilder();
                        state = State.START;
                        tokens.add(new Token(symbolType, String.valueOf(symbol)));
                        break;
                }
            }
        }
        if (!buffer.isEmpty()){
            tokens.add(new Token(state == State.FLOAT ? Type.FLOAT : Type.INT, buffer.toString()));
        }
        return tokens;
    }
}



