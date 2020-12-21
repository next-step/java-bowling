package bowling.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.BowlingScore;

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
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean isFinalPlayable() {
        return false;
    }
}
