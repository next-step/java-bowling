package bowling.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    public static final String ERROR_MSG_PREFIX = "[ERROR] ";
    public static final String PLAYER_NAME_INPUT_MSG = "플레이어 이름은(3 english letters)?: ";
    public static final String KNOCK_DOWN_PINS_INPUT_MSG_SUFFIX = "프레임 투구 : ";
    private final Scanner scanner;

    public ConsoleInputView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getPlayerName() {
        System.out.print(PLAYER_NAME_INPUT_MSG);
        return scanner.nextLine();
    }

    @Override
    public int getKnockDownPins(int frameIndex) {
        System.out.print(frameIndex + KNOCK_DOWN_PINS_INPUT_MSG_SUFFIX);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void printError(RuntimeException e) {
        System.out.println(ERROR_MSG_PREFIX + e.getMessage());
    }
}
