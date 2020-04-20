package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Calculator;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreCalculator;
import bowling.exception.BowlingException;

public class Miss implements State {

    private static final String PINS_STATE = "%3d|%d ";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(final Pins firstPins, final Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException(CANT_THROW_BALL);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String getCurrentPinsState() {
        if (firstPins.isGutter()) {
            return String.format(" -|%d  ", secondPins.getDownPin());
        }

        if (secondPins.isGutter()) {
            return String.format("  %d|- ", firstPins.getDownPin());
        }

        return String.format(PINS_STATE, firstPins.getDownPin(), secondPins.getDownPin());
    }

    @Override
    public Score getCurrentScore() {
        return new Score(firstPins.getDownPin() + secondPins.getDownPin(), 0);
    }

    @Override
    public Calculator getCurrentCalculator() {
        return new ScoreCalculator(new Score(firstPins.getDownPin() + secondPins.getDownPin()), 0);
    }

    @Override
    public Score getCalculateScore(Score before) {
        before = before.addScore(new Score(firstPins.getDownPin()));

        if (before.canAddNextScore()) {
            return before.addScore(new Score(secondPins.getDownPin()));
        }

        return before;
    }

    @Override
    public Calculator getScoreCalculate(Calculator before) {
        before = before.sumScore(new Score(firstPins.getDownPin()));

        if (before.canAddNextScore()) {
            return before.sumScore(new Score(secondPins.getDownPin()));
        }

        return before;
    }
}
