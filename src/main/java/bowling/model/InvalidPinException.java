package bowling.model;

/**
 * The type Invalid pin exception.
 */
public class InvalidPinException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "핀은 %d ~ %d 값이어야 합니다 (입력값: %s)";

    InvalidPinException(int min, int max, int inputNumber) {
        super(String.format(ERROR_MESSAGE, min, max, inputNumber));
    }
}