package bowling.domain.state;

import bowling.domain.value.Pins;
import bowling.domain.value.Score;
import bowling.utils.Preconditions;

public class SecondGutter extends FinishState {
    private final Pins firstPins;
    private final Pins secondPins;

    private SecondGutter(Pins firstPins, Pins secondPins) {
        Preconditions.checkMaximumSize(firstPins.add(secondPins), MAXIMUM_COUNT, "최대 투구수를 넘을 수 없습니다.");

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPin, Pins secondPins) {
        return new SecondGutter(firstPin, secondPins);
    }

    @Override
    public Score calculateScore() {
        return Score.ofMiss(firstPins.add(secondPins));
    }

    @Override
    public int countPins() {
        return firstPins.add(secondPins);
    }

    @Override
    public String getMark() {
        return checkGutter(firstPins) + DELIMITER + checkGutter(secondPins);
    }
}
