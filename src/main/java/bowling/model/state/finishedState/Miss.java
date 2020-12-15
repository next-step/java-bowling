package bowling.model.state.finishedState;

import bowling.model.Pins;

public class Miss extends FinishedState {
    private static final String MISS_TOTAL_SCORE_ERROR = "Miss의 조건에 맞지 않습니다.";

    private Miss(Pins pins) {
        super(pins);
    }

    public static Miss from(Pins pins) {
        if (pins.isMaxScore()) {
            throw new IllegalArgumentException(MISS_TOTAL_SCORE_ERROR);
        }
        return new Miss(pins);
    }

    @Override
    public String toString() {
        return pins.toString();
    }
}
