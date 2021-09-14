package step3.state;

import java.util.Objects;
import step3.domain.Pins;
import step3.domain.Score;

public class FinalEnd extends Finished {
    private final Pins pins;
    private final Score score;

    public FinalEnd(int fallenPins) {
        this.score = new Score(fallenPins, 0);
        pins = new Pins(fallenPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinalEnd strike = (FinalEnd) o;
        return Objects.equals(score, strike.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return pins.sumScore(beforeScore);
    }


    @Override
    public String symbol() {
        if (pins.getFallenPins() == 10) {
            return "X";
        }
        if (pins.getFallenPins() == 0) {
            return "-";
        }
        return Integer.toString(pins.getFallenPins());
    }
}
