package step4.domain.state;

import java.util.Objects;
import step4.domain.Pins;
import step4.domain.Score;

public class Strike implements State{
    private int falledPins;
    private Score score;

    public Strike() {
        this.falledPins = 10;
        this.score = new Score(10, 2);
        Pins.validPins(falledPins);
    }

    @Override
    public State throwBowl(int falledPins) {
        return null;
    }

    @Override
    public String getScore() {
        return score.getScore();
    }

    @Override
    public Score score() {
        return score;
    }

    public String getSymbol() {
        return "X";
    }

    @Override
    public Score calculateScore(Score beforeScore) {

        if (beforeScore.canCalculate()) {
            return beforeScore;
        }
        beforeScore = beforeScore.throwBowl(10);
        return beforeScore;
    }

    @Override
    public boolean isFinish() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Strike strike = (Strike) o;
        return falledPins == strike.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
