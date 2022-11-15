package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String INPUT_NAME_STRING = "플레이어 %d의 이름은(3 english letters)?: ";
    private static final String INPUT_DONWNPIN_STRING = "%s's turn : ";
    private static final String EXCEPTION_MESSAGE = "Wrong Input : downPinCount is not a number";
    private static final String INPUT_MESSAGE = "How many people? ";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]\\d*(\\.\\d+)?$");

    public static List<String> inputPlayerNames() {
        int inputPlayerCount = inputPlayerCount();
        Scanner scanner = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        for (int index = 0; index < inputPlayerCount; index++) {
            System.out.printf(INPUT_NAME_STRING, index + 1);
            String name = scanner.nextLine();
            names.add(name);
        }
        return names;
    }

    private static int inputPlayerCount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(INPUT_MESSAGE);
        String playerCount = scanner.nextLine();
        if (!isNumber(playerCount)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        return Integer.parseInt(playerCount);
    }

    public static int inputDownPinCount(String playerName) {
        System.out.printf(INPUT_DONWNPIN_STRING, playerName);
        Scanner scanner = new Scanner(System.in);
        String downPinCount = scanner.nextLine();
        if (!isNumber(downPinCount)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        return Integer.parseInt(downPinCount);
    }

    private static boolean isNumber(String numberString) {
        return NUMBER_PATTERN.matcher(numberString).find();
    }

}
