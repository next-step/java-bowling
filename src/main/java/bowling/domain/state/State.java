package bowling.domain.state;

import bowling.domain.value.Pins;

public interface State {
    String INVALID_PITCH = "투구할 수 없습니다.";
    String DELIMITER = "|";
    String GUTTER = "-";

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
