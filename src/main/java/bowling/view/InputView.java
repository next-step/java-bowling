package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";
    private static final String INPUT_PLAYER_COUNT_MESSAGE = "How many people?";
    private static final Scanner sc = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return sc.nextLine();
    }

    public static int inputPlayerCount() {
        System.out.println(INPUT_PLAYER_COUNT_MESSAGE);
        String input = sc.nextLine();
        validateIsDigit(input);
        return Integer.parseInt(input);
    }

    private static void validateIsDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("숫자가 아닌 문자가 입력되었습니다.");
        }
    }
}
