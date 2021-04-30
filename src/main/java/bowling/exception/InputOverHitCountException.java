package bowling.exception;

public final class InputOverHitCountException extends RuntimeException {

    private final String MESSAGE_FORMAT = "( %s )은, 남은 수 ( %s ) 보다 큰 값입니다.";

    private final int hitCount;
    private final int pinCount;

    public InputOverHitCountException(final int hitCount, final int pinCount) {
        this.hitCount = hitCount;
        this.pinCount = pinCount;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, hitCount, pinCount);
    }
}
