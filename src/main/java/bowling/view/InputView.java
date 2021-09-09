package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";

    public static String askPlayerName() {
        System.out.print(ASK_PLAYER_NAME);
        return SCANNER.nextLine();
    }
}
