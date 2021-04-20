package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME = "플레이어 이름은(3 english letters)?: ";
    public static final String CHECK_NULL_OR_EMPTY = "null 또는 공백인지 확인해주세요.";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String playerName() {
        System.out.print(INPUT_PLAYER_NAME);
        String name = scanner.nextLine().trim();
        checkNullOrEmpty(name);
        return name;
    }

    private void checkNullOrEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(CHECK_NULL_OR_EMPTY);
        }
    }
}
