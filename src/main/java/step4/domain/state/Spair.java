package step4.domain.state;

import java.util.Objects;
import step4.domain.Score;

public class Spair implements State {
    private int falledPins;
    private Score score;

    public Spair() {
        this.falledPins = 10;
        this.score = new Score(10, 1);
    }

    public State throwBowl(int falledPins) {
        return null;
    }

    @Override
    public String getScore() {
        return score.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spair spair = (Spair) o;
        return falledPins == spair.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
