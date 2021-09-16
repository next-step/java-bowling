package step3.state;

import step3.domain.Score;
import step3.exceptions.CannotCalculateExceptions;
import step3.exceptions.SymbolDoesNotExistException;

public class FinalReady implements State {

    private Score score;

    public State bowl(int fallenPins) {
        return new FinalEnd(fallenPins);
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

    @Override
    public String symbol() {
        throw new SymbolDoesNotExistException();
    }
}
