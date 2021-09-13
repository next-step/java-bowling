package bowling.exception;

public class InvalidPitchValueException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_PITCH = "투구 값은 %d ~ %d 사이의 값이어야 합니다";

    public InvalidPitchValueException(final int over, final int below) {
        super(String.format(INVALID_PITCH, over, below));
    }

}