package bowling.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.BowlingScore;
import bowling.domain.score.Score;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

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
    public boolean isPlayable() {
        return false;
    }

    @Override
    public boolean isFinalPlayable() {
        return true;
    }

}

