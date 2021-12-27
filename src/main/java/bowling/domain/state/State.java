package bowling.domain.state;

import bowling.domain.value.Pins;

public interface State {

    String GUTTER = "-";


    State bowl(Pins pins);
    boolean isFinished();
    boolean isGameOver();
    String getMark();

    default String checkGutter(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER;
        }

        return String.valueOf(pins.getPins());
    }

}
