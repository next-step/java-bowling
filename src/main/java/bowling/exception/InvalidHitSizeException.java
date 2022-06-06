package bowling.exception;

public class InvalidHitSizeException extends IllegalArgumentException {

    private static final String MESSAGE = "Hit Size 는 %d 개를 초과할 수 없습니다.";

    public InvalidHitSizeException(int maxHitSize) {
        super(String.format(MESSAGE, maxHitSize));
    }
}
