package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Result;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Hold extends Running {

    private Pin current;

    public Hold(Pin current) {
        this.current = current;
    }

    @Override
    public State roll(int count) {
        if (current.isSpare(count)) {
            return new Spare(current, count);
        }

        return new Miss(current, count);
    }

    @Override
    public List<String> toValues() {
        if (current.isGutter()) {
            return Arrays.asList(Result.GUTTER.toString());
        }
        return Arrays.asList(current.toString());
    }

    @Override
    public Score getScore() {
        throw new UnsupportedOperationException();
    }
}
