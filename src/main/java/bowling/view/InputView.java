package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String askUserNameMessage = "플레이어 이름은(3 english letters)?: ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int askDownedPins() {
        return scanner.nextInt();
    }

    public static String askName() {
        System.out.print(askUserNameMessage);
        return scanner.nextLine();
    }
}
