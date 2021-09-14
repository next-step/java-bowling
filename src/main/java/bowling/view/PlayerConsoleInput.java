package bowling.view;

import bowling.util.NumberUtils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerConsoleInput {

    private static final String PLAYER_COUNT_QUESTION = "How many people? ";
    private static final String NAME_QUESTION = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final Scanner scanner = new Scanner(System.in);

    public static int askPlayerCount() {
        System.out.print(PLAYER_COUNT_QUESTION);
        return getUntilNumber(scanner.nextLine());
    }

    public static List<String> askNames(final int playerCount) {
        return IntStream.rangeClosed(1, playerCount)
                .peek(i -> System.out.print(String.format(NAME_QUESTION, i)))
                .mapToObj(i -> scanner.nextLine())
                .collect(Collectors.toList());
    }

    private static int getUntilNumber(final String pitch) {
        if (!NumberUtils.isNumber(pitch)) {
            NumberUtils.printNotNumber(pitch);
            return askPlayerCount();
        }
        return Integer.parseInt(pitch);
    }

}
