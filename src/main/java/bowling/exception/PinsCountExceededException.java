package bowling.exception;

public final class PinsCountExceededException extends BowlingException {

    public static final String PINS_COUNT_EXCEEDED = "Pin이 너무 많습니다.";

    public PinsCountExceededException() {
        super(PINS_COUNT_EXCEEDED);
    }
}
