package bowling.domian.frame;

import bowling.domian.state.State;

import java.util.Objects;

public class NormalFrameResult extends FrameResult {
    private static final int TOTAL_SCORE_UNCALCULATED = -1;

    private NormalFrameResult(State state) {
        this(state, TOTAL_SCORE_UNCALCULATED);
    }

    private NormalFrameResult(State state, int totalScore) {
        this.state = state;
        this.totalScore = totalScore;

        if (state.canGetScore()) {
            score = state.getScore();
        }
    }

    public static NormalFrameResult get(State state) {
        return new NormalFrameResult(state);
    }

    public static NormalFrameResult get(State state, int totalScore) {
        return new NormalFrameResult(state, totalScore);
    }

    @Override
    public boolean canCalculateScore() {
        return state.canGetScore();
    }

    @Override
    public boolean isCalculateDone() {
        if (!state.canGetScore() || Objects.isNull(score)) {
            return false;
        }

        return this.score.isCalculateDone() & isTotalCalculated();
    }

    @Override
    public void calculateAdditional(FrameResult lastNormalFrameResult) {
        Score additionalScore = this.state.calculateAdditional(lastNormalFrameResult.score);

        if (this.state.isFinished() || additionalScore.isCalculateDone()) {
            lastNormalFrameResult.score = additionalScore;
        }
    }
}
