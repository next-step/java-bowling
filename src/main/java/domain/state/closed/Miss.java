package domain.state.closed;

import domain.Pins;
import domain.Score;

import static view.OutputView.SEPARATOR;

public class Miss extends Closed {
    static final String ALERT_CANNOT_BE_MISS = "MISS가 아닙니다.";
    public static final String GUTTER_SYMBOL = "-";

    private Pins firstFallenPins;
    private Pins secondFallenPins;

    public Miss(Pins firstFallenPins, Pins secondFallenPins) {
        if (firstFallenPins.exceedMiss(secondFallenPins)) {
            throw new IllegalArgumentException(ALERT_CANNOT_BE_MISS);
        }
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
    }

    @Override
    public String printState() {
        String firstPins = firstFallenPins.toString();
        String secondPins = secondFallenPins.toString();
        if (firstFallenPins.isMatch(Pins.from(0))) {
            firstPins = GUTTER_SYMBOL;
        }
        if (secondFallenPins.isMatch(Pins.from(0))) {
            secondPins = GUTTER_SYMBOL;
        }
        return firstPins + SEPARATOR + secondPins;
    }

    @Override
    public Score getScore() {
        int sumOfPins = firstFallenPins.sumPins(secondFallenPins);
        return Score.of(sumOfPins, 0);
    }
}
