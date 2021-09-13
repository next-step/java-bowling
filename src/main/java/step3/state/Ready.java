package step3.state;

import step3.Score;
import step3.exceptions.CannotCalculateExceptions;

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

    @Override
    public boolean isFinish() {
        return false;
    }
}
