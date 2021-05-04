package bowling.ui;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private final static String MESSAGE_INPUT_PLAYER_COUNT = "How many people? ";
    private final static String MESSAGE_INPUT_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)? : ";
    private final static String MESSAGE_INPUT_BOWLING_PIN = "'s turn : ";

    public static String inputPlayerCount() {
        System.out.print(MESSAGE_INPUT_PLAYER_COUNT);
        return scanner.nextLine();
    }

    public static String inputPlayerName(int number) {
        System.out.printf(MESSAGE_INPUT_PLAYER_NAME, number);
        return scanner.nextLine();
    }

    public static int inputBowlingPin(String playerName) {
        System.out.print(playerName + MESSAGE_INPUT_BOWLING_PIN);
        return scanner.nextInt();
    }
}
