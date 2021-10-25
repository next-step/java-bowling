package bowling.exception.frame;

import bowling.exception.CustomException;

public class NextFrameNotFoundException extends CustomException {

    private static final String NEXT_FRAME_NOT_FOUND_ERROR_MESSAGE = "next frame가 존재하지 않습니다.";

    public NextFrameNotFoundException(String message) {
        super(message);
    }

    public NextFrameNotFoundException() {
        this(NEXT_FRAME_NOT_FOUND_ERROR_MESSAGE);
    }

}
