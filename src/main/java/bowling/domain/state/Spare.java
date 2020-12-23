package bowling.domain.state;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.Symbol;

public class Spare implements State {
    public static final String DELIMITER = "|";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score getScore() {
        Score score = new Score(firstPins);
        score.setSecond(secondPins);
        return score;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String getSymbol() {
        return this.firstPins.get() + DELIMITER + Symbol.SPARE.getSymbol();
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
