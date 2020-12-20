package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String ASK_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String askName() {
        System.out.print(ASK_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int askScore() {
        return scanner.nextInt();
    }
}
