package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.println(ViewMessages.INSTRUCTION_PLAYER_NAME);
        return SCANNER.nextLine();
    }
}
