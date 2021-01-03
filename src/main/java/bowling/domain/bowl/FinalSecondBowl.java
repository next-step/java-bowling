package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.*;

import java.util.Optional;

public class FinalSecondBowl implements Bowl {
    private final State firstState;

    public FinalSecondBowl(State firstState) {
        this.firstState = firstState;
    }

    @Override
    public State stroke(Pins pins) {
        Score score = new Score(pins);
        State state2 = Optional.ofNullable(this.firstState).orElse(new None());

        if (score.isStrike()) {
            return new FinalSecondState(firstState, new Strike());
        }
        if (score.isGutter()) {
            return new FinalSecondState(firstState, new Gutter(pins, pins));
        }
        if (state2.getScore().isSpare(pins)) {
            return new FinalSecondState(firstState, new Spare(state2.getScore().getFirst(), pins));
        }
        return new FinalSecondState(firstState, new Miss(pins));
    }
}
