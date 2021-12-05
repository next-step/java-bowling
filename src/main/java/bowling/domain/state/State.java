package bowling.domain.state;

import bowling.annotations.GetterForUI;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

public interface State {
    String GUTTER = "-";

    State pitch(Pins pins);

    Score calculateScore();

    boolean isFinish();

    int countPins();

    @GetterForUI
    String getMark();

    default String checkGutter(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER;
        }

        return String.valueOf(pins.getPins());
    }
}
