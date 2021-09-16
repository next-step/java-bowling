package bowling.exception.frame;

import bowling.exception.CustomException;

public class FinalFrameCreateException extends CustomException {

    private static final String FINAL_FRAME_CREATE_ERROR_MESSAGE = "FinalFrame은 새로운 Frame을 생성할 수 없다.";

    public FinalFrameCreateException(String message) {
        super(message);
    }

    public FinalFrameCreateException() {
        this(FINAL_FRAME_CREATE_ERROR_MESSAGE);
    }

}
