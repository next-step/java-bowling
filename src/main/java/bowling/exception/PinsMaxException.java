package bowling.exception;

public class PinsMaxException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "최대 핀의 갯수는 10개 입니다.";

    public PinsMaxException() {
        super(ERROR_MESSAGE);
    }

}
