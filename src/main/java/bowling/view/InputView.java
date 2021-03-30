package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String playerName() {
        System.out.print(MessageConstant.PLAYER_NAME_INPUT);
        return SCANNER.nextLine()
                .trim();
    }

    public static String pintCounts(int frameNumber) {
        System.out.print(frameNumber+MessageConstant.PINS_COUNT);
        return SCANNER.nextLine()
                .trim();
    }
}
