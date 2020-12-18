package bowling.model.state.finished;

import bowling.model.Pins;
import bowling.model.Score;
import bowling.model.state.State;

public abstract class FinishedState implements State {
    private static final String PITCHING_ERROR = "해당 프레임에서는 더 이상 던질 수 없습니다.";

    protected Pins pins;
    protected String expression;

    protected FinishedState(Pins pins) {
        this.pins = pins;
    }

    public abstract Score calculateScore(Score score);

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State bowling(Pins fallenPin) {
        throw new IllegalArgumentException(PITCHING_ERROR);
    }

    @Override
    public String toString() {
        return expression;
    }
}
