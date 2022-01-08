package bowling.view;

import java.util.Scanner;

import bowling.engine.Name;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_COUNT = "플레이어 숫자는?";
    private static final String INPUT_NAME_MESSAGE = "플레이어 이름은(3 english letters)?:";
    private static final String INPUT_SHOT_MESSAGE = "%s's turn :";

    private InputView() {}

    public static int inputPlayerCount() {
        return inputInteger(INPUT_PLAYER_COUNT);
    }

    public static String inputName() {
        System.out.print(INPUT_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputShotResult(Name currentName) {
        return inputInteger(INPUT_SHOT_MESSAGE, currentName);
    }

    private static int inputInteger(String message, Object ... formats) {
        System.out.printf(message, formats);

        int number = scanner.nextInt();
        flush();
        return number;
    }

    private static void flush() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
