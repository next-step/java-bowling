package bowling.exception;

public final class InputOverHitCountException extends RuntimeException {

    private final String MESSAGE_FORMAT = "남은 수 ( %s ) 에 비해, 맞은 횟수 ( %s )는 더 큰 값입니다.";

    private final int pinCount;
    private final int hitCount;

    public InputOverHitCountException(final int pinCount, final int hitCount) {
        this.pinCount = pinCount;
        this.hitCount = hitCount;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, pinCount, hitCount);
    }
}
