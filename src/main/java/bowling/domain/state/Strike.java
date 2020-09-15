package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Result;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Strike implements State {

    private Pin current;

    public Strike(Pin current) {
        this.current = current;
    }

    @Override
    public State roll(int next) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getValue() {
        return Arrays.asList(Result.STRIKE.toString());
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Score getScore() {
        return new Score(current.getCount());
    }
}
