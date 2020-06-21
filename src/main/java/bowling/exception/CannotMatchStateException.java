package bowling.exception;

public class CannotMatchStateException  extends IllegalArgumentException {

    private static final String CANNOT_MATCH_STATE = "해당 상태값을 가질 수 없습니다. state: %s";

    public CannotMatchStateException(final String value) {
        super(String.format(CANNOT_MATCH_STATE, value));
    }
}
