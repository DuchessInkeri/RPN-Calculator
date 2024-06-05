package calculator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TokenCollection extends ArrayList<Token> {
    @Override
    public String toString() {
        return this.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
