package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

import static bowling.model.DownPin.DOWN_ALL;
import static bowling.model.frame.state.Score.ZERO_OF_COUNT;
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
        if (totalCount.equals(DOWN_ALL)) {
            return Spare.valueOf(first);
        }
        return Miss.valueOf(first, second);
    }

    @Override
    public Score getScore() {
        return Score.of(ZERO_OF_COUNT, first);
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