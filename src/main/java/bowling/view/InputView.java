package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private static final String INPUT_PLAYER_COUNT = "How many people? ";
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_SCORE_MESSAGE_TEMPLATE = "%s's turn : ";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public int getInputPlayerCount() {
        System.out.print(INPUT_PLAYER_COUNT);
        return Integer.parseInt(scanner.nextLine());
    }

    public List<String> getInputPlayerNames(final int playerCount) {
        List<String> players = new ArrayList<>();
        for (int i = 1; i < playerCount + 1; i++) {
            players.add(getInputPlayerName(i));
        }
        return players;
    }

    public String getInputPlayerName(final int index) {
        System.out.printf(INPUT_PLAYER_NAME_MESSAGE, index);
        return scanner.nextLine();
    }

    public int getInputScore(final String playerName) {
        System.out.println();
        System.out.printf(INPUT_SCORE_MESSAGE_TEMPLATE, playerName);
        return Integer.parseInt(scanner.nextLine());
    }
}