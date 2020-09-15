package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Result;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Finish implements State {

    private Pin previous;
    private Pin current;

    private Finish(Pin previous, int count) {
        this.previous = previous;
        this.current = Pin.of(count);
    }

    static State from(State current, int count) {
        Pin pin = Pin.of(count);

        if (pin.isStrike()) {
            return new Strike(pin);
        }

        return new Finish(Pin.of(current.getScore().toInt()), count);
    }

    @Override
    public State roll(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getValue() {
        if (current.isGutter()) {
            return Arrays.asList(Result.GUTTER.toString());
        }

        if (current.isSpare(previous.getCount())) {
            return Arrays.asList(Result.SPARE.toString());
        }

        return Arrays.asList(current.toString());
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.ofOpen(current.getCount());
    }
}