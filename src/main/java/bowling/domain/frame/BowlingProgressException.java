package bowling.domain.frame;

public class BowlingProgressException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "게임을 더 진행할 수 없는 상태입니다.";

    public BowlingProgressException() {
        this(DEFAULT_MESSAGE);
    }

    public BowlingProgressException(String message) {
        super(message);
    }
}
