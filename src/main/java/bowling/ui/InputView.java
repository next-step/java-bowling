package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private final static String MESSAGE_INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    private final static String MESSAGE_INPUT_BOWLING_PIN = "프레임 투구 : ";

    public static String inputPlayerName() {
        System.out.print(MESSAGE_INPUT_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static int inputBowlingPin(int currentFrameNumber) {
        System.out.print(currentFrameNumber + MESSAGE_INPUT_BOWLING_PIN);
        return scanner.nextInt();
    }
}
