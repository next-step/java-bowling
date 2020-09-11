package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ASK_PLAYER_NAME = "플레이어 이름은(3 english letters)?";

    private InputView() {
    }

    public static String scanPlayer() {
        System.out.println(ASK_PLAYER_NAME);

        return scanner.nextLine();
    }
}

