package bowling.exception;

public final class CanNotCalculateException extends IllegalArgumentException {

    private static final String MESSAGE = "점수를 계산할 수 없습니다.";

    public CanNotCalculateException() {
        super(MESSAGE);
    }
}
