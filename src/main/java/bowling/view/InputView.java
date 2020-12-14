package bowling.view;

import bowling.bowler.Bowler;
import bowling.global.utils.StringParser;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.global.utils.CommonConstant.NUMBER_ONE;
import static bowling.global.utils.ExceptionMessage.INVALID_INPUT_PLAYER_NUMBER;

public class InputView {

    private static final String PRINT_INPUT_PLAYER_NUMBER = "How many people? ";
    private static final String PRINT_INPUT_PLAYER_NAME = "플레이어 %d의 이름은? (3 english letters) : ";
    private static final String PRINT_INPUT_PLAYER_PITCH = "%3s's turn : ";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern PATTERN_PLAYER_NUMBER = Pattern.compile("^[0-9]+$");

    private InputView() {
    }

    public static int inputPlayerNumber() {
        System.out.print(PRINT_INPUT_PLAYER_NUMBER);
        String playerNumber = scanner.nextLine();

        if (!playerNumber.matches(PATTERN_PLAYER_NUMBER.pattern())) {
            throw new IllegalArgumentException(INVALID_INPUT_PLAYER_NUMBER);
        }

        return new StringParser(playerNumber).toInt();
    }

    public static String inputPlayerName(int playerNumber) {
        System.out.printf(PRINT_INPUT_PLAYER_NAME, playerNumber);
        return scanner.nextLine();
    }

    public static List<Bowler> getBowlers(int playerNumber) {
        return IntStream.rangeClosed(NUMBER_ONE, playerNumber)
                .mapToObj(InputView::inputPlayerName)
                .map(Bowler::of)
                .collect(Collectors.toList());
    }

    public static String inputPins(String playerName) {
        System.out.printf(PRINT_INPUT_PLAYER_PITCH, playerName);
        return scanner.nextLine();
    }

}
