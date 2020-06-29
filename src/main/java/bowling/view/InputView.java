package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_BOWLING_COUNT_FRAME_MESSAGE = "프레임 투구 : ";

    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputDropPinCount(final int frameCount) {
        System.out.println(frameCount + INPUT_BOWLING_COUNT_FRAME_MESSAGE);
        final int pinCount = scanner.nextInt();

        scanner.nextLine();
        return pinCount;
    }
}
