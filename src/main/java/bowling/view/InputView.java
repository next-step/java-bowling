package bowling.view;

import bowling.domain.dto.BowlerData;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final String INPUT_NUM_OF_PLAYERS = "How many people? ";
    private static final String INPUT_PLAYER_NAME = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_NUM_OF_PIN_FORMAT = "%s's turn : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        print(INPUT_NUM_OF_PLAYERS);
        int numberOfPlayers = getInputIntValue();

        return IntStream.range(0, numberOfPlayers)
                .mapToObj(InputView::inputPlayerName)
                .collect(Collectors.toList());
    }

    public static String inputPlayerName(int index) {
        print(String.format(INPUT_PLAYER_NAME, index + 1));

        return getInputValue();
    }

    public static int inputCountOfHitPins(BowlerData bowlerData) {
        print(String.format(INPUT_NUM_OF_PIN_FORMAT, bowlerData.getBowlerName()));
        return getInputIntValue();
    }

    private static void print(String statement) {
        System.out.print(statement);
    }

    private static int getInputIntValue() {
        return Integer.parseInt(getInputValue());
    }

    private static String getInputValue() {
        return SCANNER.nextLine().trim();
    }
}
