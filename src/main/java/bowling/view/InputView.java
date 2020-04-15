package bowling.view;

import bowling.exception.BowlingException;

import java.util.Scanner;

public class InputView {
    private static final String PLAYER_INPUT_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    public static String inputPlayerNmae() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(PLAYER_INPUT_MESSAGE);
        String input = scanner.nextLine().trim();

        return input;
    }
}
