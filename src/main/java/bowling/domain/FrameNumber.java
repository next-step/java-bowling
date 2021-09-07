package bowling.domain;

import bowling.exception.InputException;

public class FrameNumber {
    private static final int MAX_NUMBER = 10;
    private static final int NEXT_OPERATION = 1;
    private static final String CREATE_FRAME_NUMBER_ERROR = "프레임은 " + MAX_NUMBER + "개 입니다.";

    private final int frameNumber;

    public FrameNumber(final int frameNumber) {
        if (frameNumber > MAX_NUMBER) {
            throw new InputException(CREATE_FRAME_NUMBER_ERROR);
        }
        this.frameNumber = frameNumber;
    }

    public boolean isLastNumber() {
        return valueOfNextNumber() == MAX_NUMBER;
    }

    public int valueOfNextNumber() {
        return frameNumber + NEXT_OPERATION;
    }
}
