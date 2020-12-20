package bowling.state;

import bowling.domain.frame.Frame;

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
    public boolean isPlayable() {
        return false;
    }

    @Override
    public boolean isFinalPlayable() {
        return true;
    }

    @Override
    public void showResults() {
    }
}
