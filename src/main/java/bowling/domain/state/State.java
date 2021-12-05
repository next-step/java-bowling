package bowling.domain.state;

import bowling.domain.value.Pins;
import bowling.domain.value.Score;

public interface State {
    String INVALID_PITCH = "투구할 수 없습니다.";
    String DELIMITER = "|";
    String GUTTER = "-";

    int MAXIMUM_COUNT = 10;

    State pitch(Pins pins);

    Score calculateScore();

    boolean isFinish();

    String mark();

    default String checkGutter(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER;
        }

        return String.valueOf(pins.getPins());
    }
}
