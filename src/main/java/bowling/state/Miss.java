package bowling.state;

import bowling.domain.Frame;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Miss implements BowlingState {

    private final Frame frame;

    private Miss(Frame frame) {
        this.frame = frame;
    }

    public BowlingState of(Frame frame) {
        return new Miss(frame);
    }


    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public int getLeft() {
        return frame.getLeft();
    }

    @Override
    public int getFirstPitch() {
        return frame.getFirstPitch();
    }

    @Override
    public int getSecondPitch() {
        return frame.getSecondPitch();
    }

}
