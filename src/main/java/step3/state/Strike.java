package step3.state;

import java.util.Objects;
import step3.domain.Pins;
import step3.domain.Score;

public class Strike extends Finished {

    private Pins pins;
    private Score score;

    public Strike() {
        this.score = new Score(10, 2);
        this.pins = new Pins(10);
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
        if (score.canCalculateScore()) {
            return score;
        }
        return beforeScore.bowl(10);
    }


    @Override
    public String symbol() {
        return "X";
    }
}
