package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.state.FinishStateBowlException;
import bowling.exception.state.SpareStateFirstPinStrikeException;
import bowling.exception.state.SpareStatePinsException;

public class Spare implements State {

    private final Pin first;
    private final Pin second;

    public Spare(Pin first, Pin second) {
        checkFirstPinStrike(first);
        checkPinsSpare(first, second);

        this.first = first;
        this.second = second;
    }

    private static void checkFirstPinStrike(Pin first) {
        if (first.isStrike()) {
            throw new SpareStateFirstPinStrikeException();
        }
    }

    private static void checkPinsSpare(Pin first, Pin second) {
        if (!first.isSpare(second)) {
            throw new SpareStatePinsException();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Score createScore() {
        return null;
    }

    @Override
    public State bowl(Pin pin) {
        throw new FinishStateBowlException();
    }

}
