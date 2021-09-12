package step3.state;

import java.util.Objects;
import step3.Score;

public class Strike extends Finished {
    private Score score;

    public Strike() {
        this.score = new Score(10, 2);
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
}
