package bowling.exception;

public class BowlingMaxCountException extends IllegalArgumentException {
    public BowlingMaxCountException(int maxCount) {
        super("볼링 핀은 최대 " + maxCount + "개입니다.");
    }
}
