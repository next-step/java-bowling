package bowling.domain.state.exception;

public class BonusBowlingException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "해당 상태는 보너스 투구를 수행할 수 없습니다.";

    public BonusBowlingException() {
        super(EXCEPTION_MESSAGE);
    }
}
