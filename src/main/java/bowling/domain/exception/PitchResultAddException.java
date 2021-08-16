package bowling.domain.exception;

public class PitchResultAddException extends RuntimeException {

    private static final String MESSAGE = "합칠 수 없는 투구 결과";

    public PitchResultAddException() {
        super(MESSAGE);
    }
}
