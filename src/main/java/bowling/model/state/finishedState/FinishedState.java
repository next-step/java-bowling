package bowling.model.state.finishedState;

import bowling.model.Pins;
import bowling.model.state.State;

public abstract class FinishedState extends State {
    private static final String PITCHING_ERROR = "해당 프레임에서는 더 이상 던질 수 없습니다.";

    protected String expression;

    protected FinishedState(Pins pins) {
        super(pins);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State bowling(int fallenPin) {
        throw new IllegalArgumentException(PITCHING_ERROR);
    }

    @Override
    public String toString() {
        return expression;
    }
}
