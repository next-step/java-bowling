package bowling.domain.frame;

import bowling.domain.bowl.NormalBowl;
import bowling.domain.bowl.NormalBowlResult;

public class NormalFrame extends AbstractFrame {

    private static final int LAST_NORMAL_FRAME_NUMBER = 9;

    private final NormalBowl normalBowl = new NormalBowl();

    public NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    @Override
    public Frame bowl(int numberOfPin) {
        normalBowl.bowl(numberOfPin);
        return isCompleted() ? createNextFrame() : this;
    }

    private boolean isCompleted() {
        return normalBowl.isCompleted();
    }

    private Frame createNextFrame() {
        nextFrame = (frameNumber == LAST_NORMAL_FRAME_NUMBER) ?
                new FinalFrame() :
                new NormalFrame(frameNumber + 1);
        return nextFrame;
    }

    @Override
    public String toString() {
        return normalBowl.format();
    }

}
