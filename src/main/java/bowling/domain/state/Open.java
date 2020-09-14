package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Result;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Open implements State {

    private Pin current;
    private Pin next;

    public Open(Pin current, int count) {
        this.current = current;
        this.next = Pin.of(count);
    }

    @Override
    public State roll(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> value() {
        if (isDoubleGutter()) {
            return Arrays.asList(Result.GUTTER.toString(), Result.GUTTER.toString());
        }

        if (current.isGutter()) {
            return Arrays.asList(Result.GUTTER.toString(), next.toString());
        }

        if (next.isGutter()) {
            return Arrays.asList(current.toString(), Result.GUTTER.toString());
        }

        return Arrays.asList(current.toString(), next.toString());
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    private boolean isDoubleGutter() {
        return next.isGutter() && current.isGutter();
    }
}