package bowling.util.validator;

public class NumberValidator {
    private NumberValidator() {
    }

    public static void validatePositiveNumber(int number, String target) {
        if (!isPositiveNumber(number)) {
            throw new IllegalArgumentException(String.format("%s은(는) 0보다 큰 수여야 합니다..", target));
        }
    }

    private static boolean isPositiveNumber(int number) {
        return number > 0;
    }
}
