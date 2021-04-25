package bowling.domain.exception;

public class CannotBowlException extends RuntimeException {
    private static final String MESSAGE = "더 이상 투구할 수 없습니다.";
    public CannotBowlException() {
        super(MESSAGE);
    }
}
