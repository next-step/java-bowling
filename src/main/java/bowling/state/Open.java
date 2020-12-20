package bowling.state;

import bowling.domain.Frame;

/**
 * Created By mand2 on 2020-12-19.
 */
public class Open implements BowlingState {

    private final Frame frame;

    private Open(Frame frame) {
        this.frame = frame;
    }

    public static BowlingState of(Frame frame) {
        return new Open(frame);
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
