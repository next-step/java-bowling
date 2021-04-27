package bowling.exception;

public final class InputOverHitCountException extends RuntimeException {

    private final String MESSAGE_FORMAT = "맞은 갯수 (%s) 은, 현재 남아있는 갯수 (%s) 보다 큽니다.";

    private final int hitCount;
    private final int remainCount;

    public InputOverHitCountException(int hitCount, int remainCount) {
        this.hitCount = hitCount;
        this.remainCount = remainCount;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, hitCount, remainCount);
    }
}
