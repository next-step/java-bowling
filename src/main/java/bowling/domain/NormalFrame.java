package bowling.domain;

import bowling.exception.BowlingException;

public class NormalFrame {

    private static final String MAX_FRAME_MESSAGE = "1~10번 프레임 까지만 등록 가능 합니다.";
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private final Tern tern;
    private final int frameNumber;
    private final Frame nextFrame;

    public NormalFrame(int frameNumber) {
        validateFrameNumber(frameNumber);
        this.tern = Tern.FIRST;
        this.frameNumber = frameNumber;
        this.nextFrame = null;
    }

    public void validateFrameNumber(int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
            throw new BowlingException(MAX_FRAME_MESSAGE);
        }
    }
}
