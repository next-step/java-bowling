package bowling.exception;

public final class InsufficientMissCountException extends RuntimeException {

    private final String MESSAGE_FORMAT = "( %s )와 ( %s )의 합이 9보다 작지 않습니다.";

    private final int firstCount;
    private final int secondCount;

    public InsufficientMissCountException(final int firstCount, final int secondCount) {
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, firstCount, secondCount);
    }
}
