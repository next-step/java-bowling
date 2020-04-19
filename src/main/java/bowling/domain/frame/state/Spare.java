package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Calculator;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Spare implements State {

    private static final String PINS_STATE = "%3d|/ ";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        validatePinsCount(firstPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validatePinsCount(Pins firstPins) {
        if (firstPins.isFinish()) {
            throw new BowlingException();
        }
    }

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String getCurrentPinsState() {
        return String.format(PINS_STATE, firstPins.getDownPin());
    }

    @Override
    public Score getScore() {
        return new Score(Pins.MAX_PIN);
    }

    @Override
    public Calculator getCalculateScore(Calculator before) {
        before = before.sumScore(new Score(firstPins.getDownPin()));

        if (before.canAddNextScore()) {
            return before.sumScore(new Score(secondPins.getDownPin()));
        }

        return before;
    }
}
