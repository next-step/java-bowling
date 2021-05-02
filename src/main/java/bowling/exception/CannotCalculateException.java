package bowling.exception;

public final class CannotCalculateException extends BowlingException {
    public static final String CANNOT_CALCULATE = "점수 계산이 불가능한 상태입니다.";

    public CannotCalculateException() {
        super(CANNOT_CALCULATE);
    }
}
