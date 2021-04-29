package bowling.domain;

import java.util.regex.Pattern;

public class NumberOfPlayer {
    private final static String MESSAGE_CHECK_NUMERIC = "플레이어 수는 정수타입이여야 합니다.";
    private final static String MESSAGE_CHECK_RANGE = "플레이어 수는 양의 정수여야 합니다.";
    private final static String NUMERIC_REGEX = "^[-]?[0-9]+$";
    private final static Pattern PATTERN_NUMERIC = Pattern.compile(NUMERIC_REGEX);
    private final static int ZERO = 0;
    private int number;
    public NumberOfPlayer(int number) {
        checkRange(number);
        this.number = number;
    }
    public NumberOfPlayer(String number) {
        this(checkNumeric(number));
    }
    private static int checkNumeric(String number) {
        number = number.trim();
        if (!PATTERN_NUMERIC.matcher(number).matches()) {
            throw new IllegalArgumentException(MESSAGE_CHECK_NUMERIC);
        }
        return Integer.parseInt(number);
    }
    private static void checkRange(int number) {
        if (number <= ZERO) {
            throw new IllegalArgumentException(MESSAGE_CHECK_RANGE);
        }
    }
}
