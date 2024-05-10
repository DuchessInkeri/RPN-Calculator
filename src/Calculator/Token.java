package Calculator;


public class Token {
    public Type type;
    public String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }
    static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    boolean isNumber() {
        return this.type == Type.INT || this.type == Type.FLOAT;
    }

    boolean isLeftAssociativity() {
        return switch (this.value) {
            case "m","p" -> false;
            default -> true;
        };
    }

    boolean isUnary() {
        return this.value.equals("m") || this.value.equals("p");
    }



    @Override
    public String toString() {
        return String.format(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Token)) return false;
        Token otherToken = (Token) other;
        return type == otherToken.type && value.equals(otherToken.value);
    }
}