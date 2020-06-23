package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAMES_MESSAGE = "플레이어 이름은(3 english letters)? ";
    private static final String HIT_COUNT_MESSAGE = "%d 프레임 투구 : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayer() {
        return inputString(PLAYER_NAMES_MESSAGE);
    }

    public static int inputHitCount(final int frameNumber) {
        return inputInt(String.format(HIT_COUNT_MESSAGE, frameNumber));
    }

    private static String inputString(final String message) {
        System.out.println();
        System.out.print(message);

        return SCANNER.nextLine();
    }

    private static int inputInt(final String message) {
        return Integer.parseInt(inputString(message));
    }
}
