package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.BowlResult;

public class NormalFrame extends AbstractFrame {

    private static final int FIRST_NORMAL_FRAME_NUMBER = 1;
    private static final int LAST_NORMAL_FRAME_NUMBER = 9;

    private final Bowl bowl = new Bowl();

    private BowlResult bowlResult = new BowlResult();

    public NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_NORMAL_FRAME_NUMBER);
    }

    @Override
    public Frame bowl(int numberOfPin) {
        bowlResult = bowl.bowl(numberOfPin);
        return bowlResult.isCompleted() ? createNextFrame() : this;
    }

    private Frame createNextFrame() {
        nextFrame = (frameNumber == LAST_NORMAL_FRAME_NUMBER) ?
                new FinalFrame() :
                new NormalFrame(frameNumber + 1);
        return nextFrame;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String toString() {
        return bowlResult.format();
    }

}
