package step3.state;

import step3.FirstBowl;
import step3.Score;
import step3.exceptions.CannotCalculateExceptions;
import step3.state.State;
import step3.state.Strike;

public class Ready implements State {
    private Score score;

    public State bowl(int fallenPins) {
        if (fallenPins == 10) {
            return new Strike();
        }
        return new FirstBowl(fallenPins);
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        throw new CannotCalculateExceptions();
    }
}
