package bowling.exception;

public class PinsMinException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "최소 핀의 갯수는 0개 입니다.";

    public PinsMinException() {
        super(ERROR_MESSAGE);
    }

}
