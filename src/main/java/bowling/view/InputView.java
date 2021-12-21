package bowling.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);
    private static final Pattern intPattern = Pattern.compile("-?\\d+");
    private static final String NOT_NUMBER_MESSAGE = "값이 숫자가 아닙니다.";

    protected static int getIntValue(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        checkIsNumber(input);
        return Integer.parseInt(input);
    }

    private static void checkIsNumber(String input) {
        if(!intPattern.matcher(input).matches()) {
            throw new IllegalArgumentException(NOT_NUMBER_MESSAGE);
        }
    }

    protected static String getStringValue(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
