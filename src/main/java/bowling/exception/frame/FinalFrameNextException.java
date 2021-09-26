package bowling.exception.frame;

import bowling.exception.CustomException;

public class FinalFrameNextException extends CustomException {

    private static final String FINAL_FRAME_NEXT_FRAME_ERROR_MESSAGE = "FinalFrame은 다음 Frame을 확인할 수 없다.";

    public FinalFrameNextException(String message) {
        super(message);
    }

    public FinalFrameNextException() {
        this(FINAL_FRAME_NEXT_FRAME_ERROR_MESSAGE);
    }

}
