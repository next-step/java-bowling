package bowling.domain.state;

import bowling.domain.value.Pins;
import bowling.domain.value.Score;
import bowling.utils.Preconditions;

public class SecondGutter extends FinishState {
    private final Pins firstPins;
    private final Pins secondPins;

    private SecondGutter(Pins firstPins, Pins secondPins) {
        Preconditions.checkMaximumSize(firstPins.sum(secondPins), MAXIMUM_COUNT, "최대 투구수를 넘을 수 없습니다.");

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPin, Pins secondPins) {
        return new SecondGutter(firstPin, secondPins);
    }

    @Override
    public Score calculateScore() {
        return Score.ofMiss(firstPins.sum(secondPins));
    }

    @Override
    public String mark() {
        return checkGutter(firstPins) + DELIMITER + checkGutter(secondPins);
    }
}
