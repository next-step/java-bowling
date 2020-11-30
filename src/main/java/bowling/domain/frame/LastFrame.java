package bowling.domain.frame;

import bowling.domain.state.FrameReady;
import bowling.domain.state.State;

import java.util.List;

public class LastFrame extends Frame {
    private static final int MAX_SCORE_AT_LAST = 30;
    public static final int MAX_TRY_COUNT_AT_LAST = 3;

    private LastFrame(State state) {
        super(state);
    }

    public static LastFrame empty() {
        return new LastFrame(new FrameReady(MAX_TRY_COUNT_AT_LAST));
    }

    public static LastFrame of(State state) {
        return new LastFrame(state);
    }

    @Override
    protected int getMaxScore() {
        return MAX_SCORE_AT_LAST;
    }

    @Override
    public Integer calculateScore(Integer previousFrameScore, List<Frame> nextFrames) {
        return state.calculate(previousFrameScore);
    }
}
