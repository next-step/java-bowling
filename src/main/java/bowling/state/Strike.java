package bowling.state;

import bowling.domain.Frame;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Strike implements BowlingState {

    private final Frame frame;

    private Strike(Frame frame) {
        this.frame = frame;
    }

    public static BowlingState of(Frame frame) {
        return new Strike(frame);
    }


    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public int getFirstPitch() {
        return Frame.MAX_SCORE;
    }

    @Override
    public int getSecondPitch() {
        return 0;
    }
}
