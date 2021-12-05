package bowling.domain.state;

import bowling.domain.value.Pins;
import bowling.domain.value.Score;

public interface State {
    String GUTTER = "-";

    State pitch(Pins pins);

    Score calculateScore();

    boolean isFinish();

    int calculatePins();

    String mark();

    default String checkGutter(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER;
        }

        return String.valueOf(pins.getPins());
    }
}
