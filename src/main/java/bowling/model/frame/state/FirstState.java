package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

import static bowling.model.DownPin.DOWN_ALL;
import static java.lang.Boolean.FALSE;

public abstract class FirstState implements State {

    private final DownPin first;

    FirstState(DownPin first) {
        this.first = first;
    }

    DownPin getFirst() {
        return first;
    }

    @Override
    public State bowl(DownPin second) {
        DownPin totalCount = first.sum(second);
        if (DOWN_ALL.equals(totalCount)) {
            return Spare.valueOf(first);
        }
        return Miss.valueOf(first, second);
    }

    @Override
    public Score calculate(Score prevScore) {
        return prevScore.calculate(getScore());
    }

    @Override
    public boolean isFinished() {
        return FALSE;
    }
}