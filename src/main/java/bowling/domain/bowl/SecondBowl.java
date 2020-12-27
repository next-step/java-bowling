package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.state.*;

public class SecondBowl implements Bowl {
    private final State state;

    public SecondBowl(State state) {
        this.state = state;
    }

    @Override
    public State stroke(Pins pins) {
        if (this.state.getScore().isStrike()) {
            return new Strike();
        }
        if (this.state.getScore().isSpare(pins)) {
            return new Spare(this.state.getScore().getFirst(), pins);
        }
        if (this.state.getScore().isAllGutter(pins)) {
            return new Gutter(pins, pins);
        }
        return new Miss(this.state.getScore().getFirst(), pins);
    }
}
