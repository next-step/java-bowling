package bowling.util;

import java.util.Scanner;

import static bowling.Messages.WARNING_SCANNERUTIL_NOT_ALLOWED_NULL_EMPTY;

public class ScannerUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readLine() {
        String input = SCANNER.nextLine().trim();
        validateNullAndEmpty(input);
        return input;
    }

    private static void validateNullAndEmpty(String input) {
        if (input.isEmpty() || input == null) {
            throw new IllegalArgumentException(WARNING_SCANNERUTIL_NOT_ALLOWED_NULL_EMPTY);
        }
    }
}