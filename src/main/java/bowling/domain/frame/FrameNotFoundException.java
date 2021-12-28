package bowling.domain.frame;

public class FrameNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "프레임을 찾을 수 없어요.";

    public FrameNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public FrameNotFoundException(String message) {
        super(message);
    }

}
