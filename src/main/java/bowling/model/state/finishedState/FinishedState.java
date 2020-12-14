package bowling.model.state.finishedState;

import bowling.model.Score;
import bowling.model.state.State;

public abstract class FinishedState extends State {
    private static final String PITCHING_ERROR = "해당 프레임에서는 더 이상 던질 수 없습니다.";
    private static final String TOTAL_SCORE_ERROR = "이전 스코어는 합보다 클 수 없습니다.";

    protected String expression;
    protected Score previousScore;

    protected FinishedState(Score previousScore, Score totalScore) {
        if (totalScore.compareTo(previousScore) < 0) {
            throw new IllegalArgumentException(TOTAL_SCORE_ERROR);
        }
        this.score = totalScore;
        this.previousScore = previousScore;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    public State bowling(int fallenPin) {
        throw new IllegalArgumentException(PITCHING_ERROR);
    }

    @Override
    public String toString() {
        return expression;
    }
}
