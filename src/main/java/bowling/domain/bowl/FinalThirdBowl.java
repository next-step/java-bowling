package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.*;

public class FinalThirdBowl implements Bowl {
    private final State firstState;
    private final State secondState;

    public FinalThirdBowl(State firstState, State secondState) {
        this.firstState = firstState;
        this.secondState = secondState;
    }

    @Override
    public State stroke(Pins pins) {
        Score score = new Score(pins);

        if (score.isStrike()) {
            return new FinalThirdState(secondState, new Strike());
        }
        if (score.isSpare(pins)) {
            return new FinalThirdState(secondState, new Spare(firstState.getScore().getFirst(), pins));
        }
        if (score.isAllGutter(pins)) {
            return new FinalThirdState(secondState, new Gutter(pins, pins));
        }
        return new FinalThirdState(secondState, new Miss(pins));
    }
}
