package bowling.domain.exception;

import bowling.domain.frame.Frame;

public class InvalidFramesException extends RuntimeException {

    private static final String INVALID_FRAMES_SIZE_MESSAGE_FORMAT = "올바르지 않은 프레임의 크기입니다 : %d";

    public InvalidFramesException(int size) {
        super(String.format(INVALID_FRAMES_SIZE_MESSAGE_FORMAT, size));
    }

    public InvalidFramesException(Frame frame, int index) {
        super(String.format("%d번 인덱스의 프레임은 %s 일 입니다.", index, frame.getClass()));
    }

}
