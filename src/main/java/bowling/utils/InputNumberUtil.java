package bowling.utils;

public class InputNumberUtil {

    public static int parseInt(String input) {
        validateStringNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateStringNumber(String input) {
        if (!isDigit(input)) {
            throw new IllegalArgumentException("숫자가 아닌 문자가 입력되어있습니다.");
        }
    }

    private static boolean isDigit(String input) {
        return input.chars().allMatch(Character::isDigit);
    }
}
