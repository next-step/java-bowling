package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_COMMENT = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner input = new Scanner(System.in);

    public static String getPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_COMMENT);
        return input.nextLine();
    }
}
