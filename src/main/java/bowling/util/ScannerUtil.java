package bowling.util;

import java.util.Scanner;

import static bowling.Messages.*;

public class ScannerUtil {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int MIN_POSITIVE = 1;

    public static String readLine() {
        String input = SCANNER.nextLine().trim();
        validateNullAndEmpty(input);
        return input;
    }

    public static int readInt() {
        try {
            return validatePositive(Integer.parseInt(readLine()));

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WARNING_PLAYERCOUNT_NON_NUMBER_INPUT);
        }
    }

    private static void validateNullAndEmpty(String input) {
        if (input.isEmpty() || input == null) {
            throw new IllegalArgumentException(WARNING_SCANNERUTIL_NOT_ALLOWED_NULL_EMPTY);
        }
    }

    private static int validatePositive(int input) {
        if (input < MIN_POSITIVE) {
            throw new IllegalArgumentException(WARNING_PLAYERCOUNT_NON_POSITIVE);
        }
        return input;
    }
}