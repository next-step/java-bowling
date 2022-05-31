package bowling.view;

import bowling.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int inputPlayerCount() {
        System.out.println("How many people?");

        int playerCount = inputNextInt();
        flushNewLine();
        return playerCount;
    }

    public static String inputPlayerName(int playerNumber) {
        System.out.printf("플레이어 %s의 이름은?(3 english letters): %n", playerNumber);
        return SCANNER.nextLine();
    }

    public static int inputPinCount(Player player) {
        System.out.printf("%s's turn : %n", player);

        int pinCount = inputNextInt();
        flushNewLine();
        return pinCount;
    }

    private static int inputNextInt() {
        return SCANNER.nextInt();
    }

    private static void flushNewLine() {
        SCANNER.nextLine();
    }
}
