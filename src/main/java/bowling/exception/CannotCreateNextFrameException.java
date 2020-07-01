package bowling.exception;

public class CannotCreateNextFrameException extends IllegalArgumentException {

    private static final String CANNOT_CREATE_ON_READY_FRAME = "준비 프레임에서는 다음 프레임을 생성할 수 없습니다.";

    public CannotCreateNextFrameException() {
        super(CANNOT_CREATE_ON_READY_FRAME);
    }
}
