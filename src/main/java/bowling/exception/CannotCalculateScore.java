package bowling.exception;

public class CannotCalculateScore extends IllegalStateException {
    private static final String MESSAGE = "계산을 할 수 없습니다.";

    public CannotCalculateScore() {
        super(MESSAGE);
    }
}
