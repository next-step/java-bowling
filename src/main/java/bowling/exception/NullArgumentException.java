package bowling.exception;

public class NullArgumentException extends IllegalArgumentException {
    private static final String FORMAT = "%s null이면 안됩니다";

    public NullArgumentException(String target) {
        super(String.format(FORMAT, target));
    }
}
