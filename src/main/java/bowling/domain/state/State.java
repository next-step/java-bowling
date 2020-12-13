package bowling.domain.state;

import static bowling.domain.FallingPinCount.ALL_PIN_DOWN;
import static bowling.domain.FallingPinCount.NO_PIN_DOWN;

import bowling.domain.FallingPinCount;

public interface State {

    String STRIKE = "X";
    String GUTTER = "-";

    default String convertReportPattern(FallingPinCount fallingPinCount) {
        if (ALL_PIN_DOWN.equals(fallingPinCount)) {
            return STRIKE;
        }
        if (NO_PIN_DOWN.equals(fallingPinCount)) {
            return GUTTER;
        }
        return fallingPinCount.toString();
    }

    State roll(FallingPinCount fallingPinCount);

    boolean isDone();

    String reportState();
}
