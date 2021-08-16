package bowling.domain.exception;

public class PitchResultCreateException extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 투구 결과 초기 값";

    public PitchResultCreateException() {
        super(MESSAGE);
    }
}
