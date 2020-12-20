package bowling.state;

import bowling.domain.Frame;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Spare implements BowlingState {

    private final Frame frame;

    private Spare(Frame frame) {
        this.frame = frame;
    }

    public static BowlingState of(Frame frame) {
        return new Spare(frame);
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public int getLeft() {
        return 0;
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
