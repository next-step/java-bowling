package bowling.domian.frame;

import bowling.domian.state.State;

import java.util.Objects;

public class NormalFrameResult extends FrameResult {
    private NormalFrameResult(State state) {
        this.state = state;

        if (state.canGetScore()) {
            score = state.getScore();
        }
    }

    public static NormalFrameResult get(State state) {
        return new NormalFrameResult(state);
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

        return this.score.isCalculateDone();
    }

    @Override
    public void calculateAdditional(FrameResult lastNormalFrameResult) {
        Score additionalScore = this.state.calculateAdditional(lastNormalFrameResult.score);

        if (this.state.isFinished() || additionalScore.isCalculateDone()) {
            lastNormalFrameResult.score = additionalScore;
        }
    }
}
