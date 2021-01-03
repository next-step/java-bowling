package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String askUserName() {
        return scanner.nextLine();
    }

    public static int askScore() {
        return scanner.nextInt();
    }
}
