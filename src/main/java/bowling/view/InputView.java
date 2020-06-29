package bowling.view;

import bowling.domain.game.MultiBowlingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int INDEX_ONE = 1;

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        System.out.print(ViewMessage.INSTRUCTION_PLAYER_COUNTS);
        List<String> playerNames = new ArrayList<>();
        int playerCounts = Integer.parseInt(SCANNER.nextLine());
        IntStream.rangeClosed(INDEX_ONE, playerCounts)
                .forEach(index -> addPlayerName(index, playerNames));
        return playerNames;
    }

    private static void addPlayerName(int index, List<String> playerNames) {
        System.out.printf(ViewMessage.INSTRUCTION_PLAYER_NAME, index);
        playerNames.add(SCANNER.nextLine().toUpperCase().trim());
    }

    public static int inputPitchScore(MultiBowlingGame multiBowlingGame) {
        System.out.printf(ViewMessage.INSTRUCTION_PITCH, multiBowlingGame.getCurrentPlayerName());
        return Integer.parseInt(SCANNER.nextLine());
    }
}
