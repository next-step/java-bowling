package bowling.domain;

import bowling.domain.turn.Turn;
import bowling.exception.BowlingException;

public class NormalFrame implements Frame {

    private static final String MAX_FRAME_MESSAGE = "1~10번 프레임 까지만 등록 가능 합니다.";
    private static final int ZERO = 0;
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private final Turn turn;
    private final int frameNumber;
    private final Frame nextFrame;

    public NormalFrame(Turn turn, int frameNumber, Frame nextFrame) {
        validateFrameNumber(frameNumber);
        this.turn = turn;
        this.frameNumber = frameNumber;
        this.nextFrame = nextFrame;
    }

    public static NormalFrame from() {
        return new NormalFrame(Turn.from(), ZERO, null);
    }

    public void validateFrameNumber(int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
            throw new BowlingException(MAX_FRAME_MESSAGE);
        }
    }

    public void bowl() {


    }
}
