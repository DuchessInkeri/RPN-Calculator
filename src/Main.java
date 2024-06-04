import java.util.Scanner;

import static Calculator.Calculate.calculate;
import static Calculator.Tokenizer.tokenize;

public class Main {
    public static void main(String[] args) {
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
            double answer = calculate(tokenize(input));
            System.out.printf("Result: %s%n", answer);
            System.out.println("Continue [Y/N]");
            String string = scanner.nextLine();
            if (string.equalsIgnoreCase("y")) {
                System.out.println("Please enter your expression:");
            } else if (string.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid input");
                break;
            }
        }
    }
}