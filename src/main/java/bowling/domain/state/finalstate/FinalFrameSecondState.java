package bowling.domain.state.finalstate;

import static bowling.domain.FallingPinCount.ALL_PIN_DOWN;

import bowling.domain.FallingPinCount;
import bowling.domain.state.Open;
import bowling.domain.state.State;

public class FinalFrameSecondState extends Open {

    private static final String SPARE_REPORT_PATTERN = "/";

    public FinalFrameSecondState(FallingPinCount first, FallingPinCount second) {
        super(first, second);
    }

    protected boolean isSpare() {
        return ALL_PIN_DOWN.equals(FallingPinCount.sum(first, second));
    }

    protected boolean containsStrike() {
        return ALL_PIN_DOWN.equals(first) || ALL_PIN_DOWN.equals(second);
    }

    protected String getSecondReportPattern(FallingPinCount second) {

        return isSpare() ? SPARE_REPORT_PATTERN : convertReportPattern(second);
    }

    @Override
    public State roll(FallingPinCount fallingPinCount) {
        if (!isDone()) {
            return new FinalFrameBonusState(first, second, fallingPinCount);
        }
        return this;
    }

    @Override
    public boolean isDone() {
        return !containsStrike() && !isSpare();
    }

    @Override
    public String reportState() {
        return convertReportPattern(first) + SPLITTER + getSecondReportPattern(second);
    }
}
