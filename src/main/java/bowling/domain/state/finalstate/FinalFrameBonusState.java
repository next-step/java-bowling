package bowling.domain.state.finalstate;

import bowling.domain.FallingPinCount;
import bowling.domain.state.Open;
import bowling.domain.state.State;

public class FinalFrameBonusState extends FinalFrameSecondState {

    private final FallingPinCount bonus;

    public FinalFrameBonusState(FallingPinCount first, FallingPinCount second, FallingPinCount bonus) {
        super(first, second);
        this.bonus = bonus;
    }

    @Override
    public State roll(FallingPinCount downedPinCount) {
        return this;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String reportState() {
        return super.reportState() + Open.SPLITTER + convertReportPattern(bonus);
    }
}
