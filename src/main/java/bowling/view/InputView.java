package bowling.view;

import bowling.domain.Frame;

import java.util.Scanner;

public class InputView {
    private static String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?:";
    private static String INPUT_PIN_COUNT = "%s프레임 투구 :";
    private static Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static int inputPinCount(Frame frame) {
        System.out.printf(INPUT_PIN_COUNT, frame.getFrameNumber());
        int pinCount = scanner.nextInt();
        scanner.nextLine();
        return pinCount;
    }
}
