package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_NAME = "Input player name : ";
    private static final String INPUT_DRAW_PINS = "Input draw result  : ";

    private static final Scanner scanner = new Scanner(System.in);

    public static String InputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME);
        return scanner.next();
    }

    public static int inputDrawPins(int index) {
        System.out.println(index + 1 + ":" + INPUT_DRAW_PINS);
        return scanner.nextInt();
    }

}
