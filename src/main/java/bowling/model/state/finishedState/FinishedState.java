package bowling.model.state.finishedState;

import bowling.model.Score;
import bowling.model.state.State;

public abstract class FinishedState extends State {
    public final static String DELIMITER = "|";
    private final static String PITCHING_ERROR = "해당 프레임에서는 더 이상 던질 수 없습니다.";

    protected String expression;
    protected Score previousScore;

    protected FinishedState(Score previousScore, Score score) {
        this.score = score;
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
        return previousScore + DELIMITER + expression;
    }
}
