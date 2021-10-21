package bowling.view;

import java.util.Scanner;

import static bowling.model.BowlingValidator.isBlank;

public class InputView {
    public static String ask(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        String input = scanner.next();
        isBlank(input);
        return input;
    }

    public static int askDigit(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        int input = scanner.nextInt();
        return input;
    }
}
