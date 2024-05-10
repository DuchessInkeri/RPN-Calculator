import Calculator.*;

import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public static void main() {
    String hello = """ 
            Hello, friend! This is CLI (Command Line Interface) calculator.
            Please enter your expression:
            """;
    System.out.println(hello);
    Scanner scanner = new Scanner(System.in);

    while (true) {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            continue;
        }
        var tokens = Tokenizer.tokenize(input);
        var result = RPN.convert(tokens);
        System.out.println(STR."RPN:\n\{result}");
        var calculate = Calculate.calculate(tokens);
        System.out.println(STR."Result: \{calculate.pop().doubleValue()}");

        boolean cont = true;
        while (cont) {
            System.out.println("Continue [Y/N]");
            String answer = scanner.nextLine();
            if (answer.toLowerCase().equals("y")) {
                System.out.println("Please enter your expression:");
                cont = false;
            } else if (answer.toLowerCase().equals("n")) {
                cont = false;
                return;
            }
        }
    }
}

