package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.FrameReady;
import bowling.domain.state.State;

import java.util.List;

public class LastFrame extends Frame {
    private static final int MAX_SCORE_AT_LAST = 30;
    public static final int MAX_TRY_COUNT_AT_LAST = 3;

    private LastFrame(Scores scores, State state) {
        super(scores, state);
    }

    public static LastFrame empty() {
        return new LastFrame(Scores.empty(), new FrameReady(MAX_TRY_COUNT_AT_LAST));
    }

    public static LastFrame of(List<Score> scores, State state) {
        return new LastFrame(Scores.of(scores), state);
    }

    @Override
    protected int getMaxScore() {
        return MAX_SCORE_AT_LAST;
    }

    @Override
    protected Integer calculate(Integer previousFrameScore, List<Frame> nextFrames) {
        return scores.calculate(previousFrameScore);
    }
}
