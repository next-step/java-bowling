package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Calculator;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCalculator;
import bowling.exception.BowlingException;

public class FirstBowl implements State {

    private static final String PINS_STATE = "%3d   ";

    private final Pins firstPins;

    public FirstBowl(final Pins firstPins) {
        this.firstPins = firstPins;
    }

    private void validatePinCount(int pinCount) {
        if (firstPins.getTotalDownPin(new Pins(pinCount)) > 10) {
            throw new BowlingException(Pins.PINS_COUNT_RANGE);
        }
    }

    @Override
    public State bowl(int pinsCount) {
        validatePinCount(pinsCount);
        Pins second = Pins.from().bowl(pinsCount);

        if (firstPins.isFinish(second)) {
            return new Spare(firstPins, second);
        }

        if (firstPins.isGutter() && second.isGutter()) {
            return new Gutter();
        }

        return new Miss(firstPins, second);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String getCurrentPinsState() {
        if (firstPins.isGutter()) {
            return "  -  ";
        }
        return String.format(PINS_STATE, firstPins.getDownPin());
    }

    @Override
    public Calculator getCurrentCalculator() {
        return new ScoreCalculator(new Score(firstPins.getDownPin()), 0);
    }

    @Override
    public Calculator getScoreCalculate(Calculator before) {
        return before.sumScore(new Score(firstPins.getDownPin()));
    }
}
