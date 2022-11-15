package bowling.view;

import bowling.domain.player.PlayerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PLAYER_COUNT_QUESTION = "How many people? ";
    private static final String PLAYER_NAME_QUESTION_FORMAT = "플레이어 %s이름은(3 english letters)?: ";
    private static final String FALLEN_PINS_QUESTION_FORMAT = "%s's turn : ";

    public static void closeScan() {
        SCANNER.close();
    }

    public static int scanPlayerCount() {
        System.out.print(PLAYER_COUNT_QUESTION);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<String> scanPlayerNames(int playerCount) {
        List<String> playerNames = new ArrayList<>();
        for (int playerNumber = 1; playerNumber <= playerCount; playerNumber++) {
            System.out.printf(PLAYER_NAME_QUESTION_FORMAT, playerNumber);
            playerNames.add(SCANNER.nextLine());
        }
        return playerNames;
    }

    public static int scanFallenPinCount(PlayerName playerName) {
        System.out.printf(FALLEN_PINS_QUESTION_FORMAT, playerName.getName());
        return Integer.parseInt(SCANNER.nextLine());
    }
}
