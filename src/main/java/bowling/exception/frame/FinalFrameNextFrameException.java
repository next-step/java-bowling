package bowling.exception.frame;

import bowling.exception.CustomException;

public class FinalFrameNextFrameException extends CustomException {

    private static final String FINAL_FRAME_NEXT_FRAME_ERROR_MESSAGE = "final frame은 다음 frame을 확인할 수 없습니다.";

    public FinalFrameNextFrameException(String message) {
        super(message);
    }

    public FinalFrameNextFrameException() {
        this(FINAL_FRAME_NEXT_FRAME_ERROR_MESSAGE);
    }

}
