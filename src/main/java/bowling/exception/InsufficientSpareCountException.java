package bowling.exception;

import bowling.util.BowlingFixture;

import static bowling.util.BowlingFixture.MAXIMUM_COUNT;

public final class InsufficientSpareCountException extends RuntimeException {

    private final String MESSAGE_FORMAT = "( %s )와 ( %s )의 합인 ( %s )는, " + MAXIMUM_COUNT + "을 충족하지 않습니다.";

    private final int firstCount;
    private final int secondCount;

    public InsufficientSpareCountException(final int firstCount, final int secondCount) {
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, firstCount, secondCount, Math.addExact(firstCount, secondCount));
    }
}
