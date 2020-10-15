package bowling.view;

import bowling.domain.Player;

import java.util.Scanner;

public class InputView {

    public static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 이름은(3 english letters)?: ";

    private static final Scanner scanner = new Scanner(System.in);
    public static final String INPUT_PITCH_COUNT_MESSAGE = "%s's turn : ";

    public static String inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return scanner.next();
    }

    public static int inputFallCount(Player player) {
        System.out.print(String.format(INPUT_PITCH_COUNT_MESSAGE, player.toString()));
        return scanner.nextInt();
    }

    public static int inputPlayerNumber() {
        System.out.print("How many people? ");
        return scanner.nextInt();
    }
}
