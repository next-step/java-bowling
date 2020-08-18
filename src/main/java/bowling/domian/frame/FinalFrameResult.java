package bowling.domian.frame;

import bowling.domian.state.State;
import bowling.domian.state.finished.Spare;
import bowling.domian.state.finished.Strike;
import bowling.domian.state.running.Ready;

import java.util.Objects;

public class FinalFrameResult extends FrameResult {

    private State bonusState;

    private FinalFrameResult(State state) {
        this(state, null);
    }

    private FinalFrameResult(State state, int totalScore) {
        this(state, null, totalScore);
    }

    private FinalFrameResult(State state, State bonusState) {
        this(state, bonusState, TOTAL_SCORE_UNCALCULATED);
    }

    private FinalFrameResult(State state, State bonusState, int totalScore) {
        this.state = state;
        this.bonusState = bonusState;
        this.score = getScore();
        this.totalScore = totalScore;
    }

    private Score getScore() {
        if (!canCalculateScore()) {
            return null;
        }
        Score score = state.getScore();

        if (Objects.nonNull(bonusState)) {
            score = bonusState.calculateAdditional(score);
        }

        return score;
    }

    public static FinalFrameResult get(State state) {
        return new FinalFrameResult(state);
    }

    public static FinalFrameResult get(State state, int totalScore) {
        return new FinalFrameResult(state, totalScore);
    }

    public static FinalFrameResult get(State state, State bonusState) {
        return new FinalFrameResult(state, bonusState);
    }

    public static FinalFrameResult get(State state, State bonusState, int totalScore) {
        return new FinalFrameResult(state, bonusState, totalScore);
    }

    @Override
    public boolean canCalculateScore() {
        if (isNormalStrikeOrSpare()) {
            return !Objects.isNull(bonusState) && !(bonusState instanceof Ready);
        }

        return state.canGetScore();
    }

    private boolean isNormalStrikeOrSpare() {
        return state instanceof Strike ||
                state instanceof Spare;
    }

    @Override
    public void calculateAdditional(FrameResult lastFrameResult) {
        Score additionalScore = this.state.calculateAdditional(lastFrameResult.score);

        if (!additionalScore.isCalculateDone()) {
            additionalScore = bonusState.calculateAdditional(lastFrameResult.score);
        }

        if (additionalScore.isCalculateDone()) {
            lastFrameResult.score = additionalScore;
        }
    }

    @Override
    public boolean isCalculateDone() {
        return Objects.nonNull(score) && isTotalCalculated();
    }

    public void addLastTotalScore(NormalFrameResult lastNormalFrameResult) {
        this.totalScore = lastNormalFrameResult.getTotalScore();
    }
}
