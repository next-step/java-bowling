package bowling.domain.bowl;

import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pins;
import bowling.domain.state.*;

import java.util.Optional;

public class SecondBowl implements Bowl {
    private final State state;

    public SecondBowl(State state) {
        this.state = state;
    }

    @Override
    public State stroke(Pins pins) {
        State state2 = Optional.ofNullable(this.state).orElse(new None());

        if (state2.getScore().isStrike()) {
            return new Strike();
        }
        if (state2.getScore().isSpare(pins)) {
            return new Spare(state2.getScore().getFirst(), pins);
        }
        if (state2.getScore().isAllGutter(pins)) {
            return new Gutter(pins, pins);
        }
        return new Miss(state2.getScore().getFirst(), pins);
    }
}
