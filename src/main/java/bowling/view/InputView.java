package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?:";

    public static String name() {
        System.out.print(INPUT_PLAYER_NAME);
        return SCANNER.next();
    }

}
