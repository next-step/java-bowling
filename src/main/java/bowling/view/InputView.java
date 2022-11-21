package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static int inputPlayers() {

        try {
            return Integer.parseInt(inputWith("How many people? "));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    private static String inputWith(final String text) {

        System.out.print(text);
        return SCANNER.next();
    }

    public static String inputPlayerName(final int index) {

        final String name = inputWith("플레이어 " + index + "의 이름은?(3 english letters): ");
        validate(name);
        return name;
    }

    private static void validate(final String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자 이름은 필수값입니다.");
        }
        if (name.length() >= 4) {
            throw new IllegalArgumentException("참가자 이름은 3자리 이하여야 합니다.");
        }
    }

    public static int inputBowlNumber(final String playerName) {

        try {
            return Integer.parseInt(inputWith(playerName + "'s turn : "));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }
}
