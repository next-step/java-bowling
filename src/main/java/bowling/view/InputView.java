package bowling.view;

import java.util.Scanner;

public class InputView {
    private InputView() {
    }

    private static final String NAME_OF_PLAYER = "플레이어 이름은(3 english letters)?:";
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUserName() {
        System.out.print(NAME_OF_PLAYER);
        return scanner.nextLine();
    }

    public static int getFelled() {
        return scanner.nextInt();
    }
}
