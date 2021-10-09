package step4.exception;

public class NextFrameDoesNotExistException extends RuntimeException {
    private static final String NEXT_FRAME_DOES_NOT_EXSIT = "다음 프레임은 존재하지 않습니다.";

    public NextFrameDoesNotExistException() {
        super(NEXT_FRAME_DOES_NOT_EXSIT);
    }
}

