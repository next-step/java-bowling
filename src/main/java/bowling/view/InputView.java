package bowling.view;

import bowling.dto.BowlingGameRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static BowlingGameRequest bowlingGameRequest() {
        int numberOfPlayer = numberOfPlayer();
        return new BowlingGameRequest(playerNames(numberOfPlayer));
    }

    private static int numberOfPlayer() {
        System.out.print(MessageConstant.NUMBER_OF_PLAYER_INPUT);
        String number = SCANNER.nextLine()
                .trim();
        return Integer.parseInt(number);
    }

    private static List<String> playerNames(int numberOfPlayer) {
        List<String> names = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayer; i++) {
            System.out.printf(MessageConstant.PLAYER_NAME_INPUT, i);
            names.add(SCANNER.nextLine().trim());
        }
        return names;
    }

    public static int pinCount(String playerName) {
        System.out.printf(MessageConstant.PLAYER_PINS_COUNT, playerName);
        String count = SCANNER.nextLine()
                .trim();
        return Integer.parseInt(count);
    }
}
