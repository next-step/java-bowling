package bowling.exception;

public class PinCountOutOfRangeException extends IllegalArgumentException {

    private static final String RANGE_OF_PIN_COUNT = "볼링 핀은 최초 0 미만, 최대 10을 넘을 수 없습니다. hitCount: %d";

    public PinCountOutOfRangeException(final int hitCount) {
        super(String.format(RANGE_OF_PIN_COUNT, hitCount));
    }
}
