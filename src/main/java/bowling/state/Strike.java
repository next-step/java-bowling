package bowling.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.BowlingScore;
import bowling.view.ResultView;

import java.util.stream.Collectors;

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
    public boolean isPlayable() {
        return false;
    }

    @Override
    public boolean isFinalPlayable() {
        return true;
    }
}
