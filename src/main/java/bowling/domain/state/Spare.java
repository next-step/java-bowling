package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Result;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Spare implements State {
    private Pin current;
    private Pin next;

    public Spare(Pin current, int count) {
        this.current = current;
        this.next = Pin.of(count);
    }

    @Override
    public State roll(int second) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> value() {
        if (current.isGutter()) {
            return Arrays.asList(Result.GUTTER.toString(), Result.SPARE.toString());
        }
        return Arrays.asList(current.toString(), Result.SPARE.toString());
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
