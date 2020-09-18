package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);
    public static final int NAME_LENGTH = 3;
    public static final String REGX_ONLY_ENGLISH_NAME = "^[a-zA-Z]*$";

    public static List<String> getNames(int playerCount) {
        return IntStream.rangeClosed(1, playerCount)
                .mapToObj(InputScanner::getName)
                .collect(Collectors.toList());
    }

    public static String getName(int playerNumber) {
        System.out.printf(String.format("플레이어 %s의 이름은?(3 english letters):", playerNumber));
        String result = scanner.next();

        if (!Pattern.matches(REGX_ONLY_ENGLISH_NAME, result)) {
            throw new IllegalArgumentException("폴레이어의 이름은 영어로만 입력가능합니다.");
        }

        if (result.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("폴레이어의 이름은 3자리로 제한됩니다.");
        }

        return result;
    }

    public static int getPlayerCount(String message) {
        return getCount(message);
    }

    public static int getHitCount(String message) {
        return getCount(message);
    }

    private static int getCount(String message) {
        System.out.print(message);
        int result = scanner.nextInt();
        System.out.println();
        return result;
    }


}