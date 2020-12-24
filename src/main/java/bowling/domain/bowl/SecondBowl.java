package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.*;

public class SecondBowl implements Bowl {
    private final Score score;

    public SecondBowl(Score score) {
        this.score = score;
    }

    @Override
    public State stroke(Pins pins) {
        // 10 프레임
        if (score.isStrike()) {
            return new Strike();
        }
        if (score.isSpare(pins)) {
            return new Spare(this.score.getFirst(), pins);
        }
        if (score.isAllGutter(pins)) {
            return new Gutter(pins, pins);
        }
        return new Miss(this.score.getFirst(), pins);
    }
}
