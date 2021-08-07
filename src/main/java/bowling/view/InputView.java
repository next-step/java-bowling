package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_COUNT_OF_DOWNED_PIN_FORMAT = "%d 프레임 투구 : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static String inputPlayerName() {
        print(INPUT_PLAYER_NAME);

        return getInputValue();
    }

    public static int inputNumOfDownedPins() {
        print(String.format(INPUT_COUNT_OF_DOWNED_PIN_FORMAT, 100));

        return getInputIntValue();
    }

    private static void print(String statement) {
        System.out.print(statement);
    }

    private static int getInputIntValue() {
        return Integer.parseInt(getInputValue());
    }

    private static String getInputValue() {
        return SCANNER.nextLine().trim();
    }
}
