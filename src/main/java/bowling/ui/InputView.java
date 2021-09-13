package bowling.ui;

import bowling.domain.BowlingGame;

import java.util.Scanner;

public class InputView {

    private static final String NUMBER_OF_PLAYER = "How many people? ";
    private static final String PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String TURN = "\n\n%s's turn : ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static int InputNumberOfPlayer() {
        System.out.print(NUMBER_OF_PLAYER);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputPlayerName(int numberOfPlayer) {
        System.out.printf(PLAYER_NAME, numberOfPlayer);
        return scanner.nextLine();
    }

    public static int nextFallenPin(BowlingGame bowlingGame) {
        System.out.printf(TURN, bowlingGame.playerName());
        return Integer.parseInt(scanner.nextLine());
    }
}
