package bowling.view;

import bowling.domain.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_COUNT = "How many people?";
    private static final String INPUT_PLAYER_NAME = "플레이어 %s의 이름은(3 english letters)?:";
    private static final String INPUT_PIN_COUNT = "%s's turn %s프레임 투구:";
    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames(int playerCount) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            System.out.printf(INPUT_PLAYER_NAME, i + 1);
            names.add(scanner.nextLine());
        }

        return names;
    }

    public static int inputPinCount(String name, Frame frame) {
        System.out.printf(INPUT_PIN_COUNT, name, frame.getFrameNumber());
        int pinCount = scanner.nextInt();
        scanner.nextLine();
        return pinCount;
    }

    public static int inputPlayerCount() {
        System.out.print(INPUT_PLAYER_COUNT);
        int pinCount = scanner.nextInt();
        scanner.nextLine();
        return pinCount;
    }
}
