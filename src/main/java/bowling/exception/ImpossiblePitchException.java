package bowling.exception;

public class ImpossiblePitchException extends RuntimeException {

    private static final String INVALID_PITCH_MESSAGE_FORMAT = "투구할 수 없습니다. : %s";

    public ImpossiblePitchException(String state) {
        super(String.format(INVALID_PITCH_MESSAGE_FORMAT, state));
    }

}
