package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_BOWLING_COUNT_FRAME_MESSAGE = "프레임 투구 : ";

    public static String inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputDropPinCount(final int frameCount) {
        System.out.print(frameCount + INPUT_BOWLING_COUNT_FRAME_MESSAGE);
        final int pinCount = scanner.nextInt();

        scanner.nextLine();
        return pinCount;
    }
}
