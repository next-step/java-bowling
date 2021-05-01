package bowling.exception;

public final class InsufficientMissCountException extends RuntimeException {

    private final String MESSAGE_FORMAT = "( %s )와 ( %s )의 합인 ( %s )는, 9이하 값을 충족하지 않습니다.";

    private final int firstCount;
    private final int secondCount;

    public InsufficientMissCountException(int firstCount, int secondCount) {
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, firstCount, secondCount, Math.addExact(firstCount, secondCount));
    }
}
