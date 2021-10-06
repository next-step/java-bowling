package step4.domain.state;

import java.util.Objects;
import step4.domain.Score;

public class LastBowl implements State {
    private int falledPins;
    private Score score;

    public LastBowl(int falledPins) {
        this.falledPins = falledPins;
        this.score = new Score(falledPins, 0);
    }

    @Override
    public State throwBowl(int falledPins) {
        return null;
    }

    @Override
    public int getScore() {
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
        LastBowl lastBowl = (LastBowl) o;
        return falledPins == lastBowl.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
