package bowling.exception.frame;

import bowling.exception.CustomException;

public class FinalFrameBowlingException extends CustomException {

    private static String FINAL_FRAME_BOWLING_ERROR_MESSAGE = "final frame이 종료되어 더이상 bowling할 수 없습니다.";

    public FinalFrameBowlingException(String message) {
        super(message);
    }

    public FinalFrameBowlingException() {
        this(FINAL_FRAME_BOWLING_ERROR_MESSAGE);
    }

}
