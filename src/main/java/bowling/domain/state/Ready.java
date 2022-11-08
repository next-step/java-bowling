package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;

import java.util.List;

public class Ready extends Running {

    @Override
    public FrameState bowl(FallenPin fallenPin) {
        if (fallenPin.isMax()) {
            return new Strike();
        }

        return new FirstBowling(fallenPin);
    }

    @Override
    public List<FallenPin> getFallenPins() {
        return List.of();
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
    public boolean isReady() {
        return true;
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
