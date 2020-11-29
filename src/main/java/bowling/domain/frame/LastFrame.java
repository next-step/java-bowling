package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.State;
import bowling.domain.state.last.LastFrameReady;

import java.util.List;

public class LastFrame extends Frame {

    private static final int MAX_SCORE_AT_LAST = 30;

    private LastFrame(Scores scores, State state) {
        super(scores, state);
    }

    public static LastFrame empty() {
        return new LastFrame(Scores.empty(), new LastFrameReady());
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
