package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final Scanner sc = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return sc.nextLine();
    }
}
