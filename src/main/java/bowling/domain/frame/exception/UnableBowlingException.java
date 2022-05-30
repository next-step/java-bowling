package bowling.domain.frame.exception;

public class UnableBowlingException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "현재 프레임은 보너스 프레임이기 때문에 더이상 투구할 수 없습니다.";

    public UnableBowlingException() {
        super(EXCEPTION_MESSAGE);
    }
}
