package bowling.state;

import bowling.domain.frame.Frame;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Miss implements BowlingState {

    private final Frame frame;

    private Miss(Frame frame) {
        this.frame = frame;
    }

    public static BowlingState of(Frame frame) {
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
    public boolean isPlayable() {
        return false;
    }

    @Override
    public boolean isFinalPlayable() {
        return false;
    }

    @Override
    public void showResults() {
    }
}
