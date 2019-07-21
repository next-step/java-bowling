package domain.state.closed;

import domain.Pins;
import domain.Score;

import static domain.Pins.STRIKE_PINS;
import static domain.state.closed.Miss.GUTTER_SYMBOL;
import static view.OutputView.SEPARATOR;

public class Spare extends Closed {
    static final String ALERT_CANNOT_BE_SPARE = "SPARE가 아닙니다.";
    private static final String SPARE_SYMBOL = "/";

    private Pins firstFallenPins;

    public Spare(Pins firstFallenPins, Pins secondFallenPins) {
        if (!firstFallenPins.isSpare(secondFallenPins)) {
            throw new IllegalArgumentException(ALERT_CANNOT_BE_SPARE);
        }
        this.firstFallenPins = firstFallenPins;
    }

    @Override
    public String printState() {
        String firstPins = firstFallenPins.toString();
        if (firstFallenPins.isMatch(Pins.from(0))) {
            firstPins = GUTTER_SYMBOL;
        }
        return firstPins + SEPARATOR + SPARE_SYMBOL;
    }

    @Override
    public Score getScore() {
        return Score.of(STRIKE_PINS, 1);
    }
}
