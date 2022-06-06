package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PLAYER_NAME_GUIDE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String BOWL_GUIDE = "\n%s's turn : ";
    private static final String PLAYER_NUMBER_GUIDE = "How many people? ";
    private static final int FIRST_PLAYER_NUMBER = 1;

    private InputView() {
    }

    public static List<String> scanPlayers() {
        System.out.println();
        int playerNumber = scanPlayerNumber();
        return scanPlayerNames(playerNumber);
    }

    private static int scanPlayerNumber() {
        System.out.print(PLAYER_NUMBER_GUIDE);
        return Integer.parseInt(scanner.nextLine());
    }

    private static List<String> scanPlayerNames(int playerNumber) {
        return IntStream.rangeClosed(FIRST_PLAYER_NUMBER, playerNumber)
                .mapToObj(InputView::scanPlayerName)
                .collect(Collectors.toList());
    }

    private static String scanPlayerName(int playerIndex) {
        System.out.printf(PLAYER_NAME_GUIDE, playerIndex);
        return scanner.nextLine();
    }

    public static String scanHitPins(String playerName) {
        System.out.printf(String.format(BOWL_GUIDE, playerName));
        return scanner.nextLine();
    }
}