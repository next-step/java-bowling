package bowling.model.state.finishedState;

import bowling.model.Pins;
import bowling.model.Score;

public class Miss extends FinishedState {
    private static final String MISS_TOTAL_SCORE_ERROR = "Miss의 조건에 맞지 않습니다.";

    private final Pins firstFallenPins;

    private Miss(Pins firstFallenPins, Pins secondFallenPins) {
        super(secondFallenPins);
        this.firstFallenPins = firstFallenPins;
    }

    @Override
    public Score score() {
        return Score.miss(pins.getScore());
    }

    public static Miss of(Pins firstFallenPins, Pins secondFallenPins) {
        Pins totalPins = firstFallenPins.add(secondFallenPins);

        if (totalPins.isMaxScore()) {
            throw new IllegalArgumentException(MISS_TOTAL_SCORE_ERROR);
        }

        return new Miss(firstFallenPins, secondFallenPins);
    }

    @Override
    public String toString() {
        return pins.toString();
    }
}
