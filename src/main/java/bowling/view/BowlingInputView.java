package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingInputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> getStringsInput(int numberOfInputString, String message) {
        return IntStream.rangeClosed(1, numberOfInputString)
                .mapToObj(i -> String.format(message, i))
                .map(BowlingInputView::getStringInput)
                .collect(Collectors.toList());
    }

    public static String getStringInput(String message) {
        System.out.print(message);

        return SCANNER.nextLine();
    }

    public static int getIntInput(String message) {
        System.out.print(message);

        return toInt(SCANNER.nextLine());
    }

    private static int toInt(String string) {
        if (string == null || !isOnlyDigits(string)) {
            throw new IllegalArgumentException("숫자로 입력되어야 합니다.");
        }

        return Integer.parseInt(string);
    }

    private static boolean isOnlyDigits(String string) {
        return string.chars().allMatch(Character::isDigit);
    }
}
