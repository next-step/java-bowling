package bowling.exception.frame;

import bowling.exception.CustomException;

public class FinalFrameCreateFrameException extends CustomException {

    private static final String FINAL_FRAME_CRATE_FRAME_ERROR_MESSAGE = "final frame은 다음 frame을 생성할 수 없습니다.";

    public FinalFrameCreateFrameException(String message) {
        super(message);
    }

    public FinalFrameCreateFrameException() {
        this(FINAL_FRAME_CRATE_FRAME_ERROR_MESSAGE);
    }

}
