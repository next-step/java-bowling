package bowling.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);
    private static final Pattern intPattern = Pattern.compile("-?\\d+");

    public static int getIntValue(String message) {
        System.out.print(message);
        String input = scanner.nextLine();
        checkIsInt(input);
        return Integer.parseInt(input);
    }

    private static void checkIsInt(String input) {
        if(!intPattern.matcher(input).matches()) {
            throw new IllegalArgumentException("값이 숫자가 아닙니다.");
        }
    }

    public static String getStringValue(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
