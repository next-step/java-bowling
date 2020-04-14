package bowling.domain;

import bowling.exception.BowlingException;

public class NormalFrame implements Frame {

    private static final String MAX_FRAME_MESSAGE = "1~10번 프레임 까지만 등록 가능 합니다.";
    private static final int ZERO = 0;
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private final Tern tern;
    private final int frameNumber;
    private final Frame nextFrame;
    private final Score score;
    private final Pins pins;
    private final Result result;

    public NormalFrame(Tern tern, int frameNumber, Frame nextFrame,
                       Score score, Pins pins, Result result) {
        validateFrameNumber(frameNumber);
        this.tern = tern;
        this.frameNumber = frameNumber;
        this.nextFrame = nextFrame;
        this.score = score;
        this.pins = pins;
        this.result = result;
    }

    public static NormalFrame from() {
        return new NormalFrame(Tern.FIRST,
                ZERO,
                null,
                Score.from(),
                Pins.from(),
                Result.READY);
    }

    public void validateFrameNumber(int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
            throw new BowlingException(MAX_FRAME_MESSAGE);
        }
    }

    public void bowl() {


    }
}
