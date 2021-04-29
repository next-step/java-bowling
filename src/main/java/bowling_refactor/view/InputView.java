package bowling_refactor.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputPlayerCount() {
        System.out.print("How many people? ");
        return SCANNER.nextInt();
    }

    public static String inputUserNames() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.next();
    }

    public static int inputPinCount(String player) {
        System.out.printf("%s's turn : ", player);
        return SCANNER.nextInt();
    }
}
