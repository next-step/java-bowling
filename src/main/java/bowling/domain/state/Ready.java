package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;

public class Ready extends Running {

    @Override
    public State bowl(FallenPin fallenPin) {
        if (fallenPin.isMax()) {
            return new Strike();
        }

        return new FirstBowling(fallenPin);
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public int tries() {
        return 0;
    }

    @Override
    public Score getScore() {
        return new Score(0, 0);
    }

    @Override
    public Score addScore(Score previousScore) {
        return previousScore;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Ready;
    }

    @Override
    public int hashCode() {
        return Ready.class.hashCode();
    }
}
