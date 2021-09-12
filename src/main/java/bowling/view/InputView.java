package bowling.view;

import bowling.model.frame.FrameNumber;

import java.io.PrintStream;
import java.util.Scanner;

public class InputView {
    private static final String PLAYER_NAME_INPUT_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String FALLEN_PIN_COUNT_INPUT_MESSAGE = "%s프레임 투구 : ";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PrintStream PRINT_STREAM = System.out;

    public static String inputPlayerName() {
        PRINT_STREAM.print(PLAYER_NAME_INPUT_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int inputFallenPinCount(FrameNumber frameNumber) {
        PRINT_STREAM.print(String.format(FALLEN_PIN_COUNT_INPUT_MESSAGE, frameNumber));
        return SCANNER.nextInt();
    }
}
