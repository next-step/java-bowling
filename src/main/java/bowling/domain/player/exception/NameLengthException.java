package bowling.domain.player.exception;

public class NameLengthException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "이름의 길이는 최대 3자까지 가능합니다.";

    public NameLengthException() {
        super(EXCEPTION_MESSAGE);
    }
}
