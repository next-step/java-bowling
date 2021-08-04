package bowling.view;

import bowling.dto.BowlingPlayerDto;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final String INPUT_NUM_OF_PLAYERS_STATEMENT = "How many people? ";
    private static final String INPUT_PLAYER_NAME_STATEMENT = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_NUM_OF_DOWNED_PIN_FORMAT = "%s's turn : ";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static List<String> inputPlayerNames() {
        printStatement(INPUT_NUM_OF_PLAYERS_STATEMENT);
        int numberOfPlayers = getInt();

        return IntStream.range(0, numberOfPlayers)
                .mapToObj(InputView::inputPlayerName)
                .collect(Collectors.toList());
    }

    public static String inputPlayerName(int idx) {
        printStatement(String.format(INPUT_PLAYER_NAME_STATEMENT, idx + 1));

        return SCANNER.nextLine()
                .trim();
    }

    public static int inputNumOfDownedPins(BowlingPlayerDto bowlingPlayerDto) {
        printStatement(String.format(INPUT_NUM_OF_DOWNED_PIN_FORMAT, bowlingPlayerDto.getName()));

        return getInt();
    }

    private static void printStatement(String statement) {
        System.out.print(statement);
    }

    private static int getInt() {
        return Integer.parseInt(
                SCANNER.nextLine()
                        .trim()
        );
    }
}
