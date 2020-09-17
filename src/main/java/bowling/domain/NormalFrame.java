package bowling.domain;

public class NormalFrame extends AbstractFrame {

    private static final int LAST_NORMAL_FRAME_NUMBER = 9;

    private final NormalBowl normalBowl = new NormalBowl();

    public NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    @Override
    public Frame bowl(int numberOfPins) {
        NormalBowlResult normalBowlResult = normalBowl.bowl(numberOfPins);
        return isCompleted(normalBowlResult) ? createNextFrame() : this;
    }

    private boolean isCompleted(NormalBowlResult normalBowlResult) {
        return !normalBowlResult.equals(NormalBowlResult.DEFAULT);
    }

    private Frame createNextFrame() {
        nextNormalFrame = (frameNumber == LAST_NORMAL_FRAME_NUMBER) ? new FinalFrame() : new NormalFrame(frameNumber + 1);
        return nextNormalFrame;
    }

    @Override
    public String toString() {
        return NormalBowlResult.getType(normalBowl)
                .format(normalBowl);
    }

}
