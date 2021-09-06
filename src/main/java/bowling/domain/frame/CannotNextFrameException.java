package bowling.domain.frame;

public class CannotNextFrameException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "모든 투구를 완료하지 않아 다음 프레임으로 진행하지 못합니다.";

    public CannotNextFrameException() {
        super(DEFAULT_MESSAGE);
    }
}
