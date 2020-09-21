package bowling.exception;

public class PinsLastPinsException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "넘어뜨린 최대 핀의 갯수는 10개 입니다.";

    public PinsLastPinsException() {
        super(ERROR_MESSAGE);
    }

}
