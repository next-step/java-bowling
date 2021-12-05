package bowling.domain.state;

import bowling.domain.value.Pins;
import bowling.domain.value.Score;
import bowling.utils.Preconditions;

public class Miss extends FinishState {
    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        Preconditions.checkMaximumSize(firstPins.add(secondPins), MAXIMUM_COUNT, "최대 투구수를 넘을 수 없습니다.");

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score calculateScore() {
        return Score.ofMiss(firstPins.add(secondPins));
    }

    @Override
    public int calculatePins() {
        return firstPins.add(secondPins);
    }

    @Override
    public String mark() {
        return checkGutter(firstPins) + DELIMITER + checkGutter(secondPins);
    }
}
