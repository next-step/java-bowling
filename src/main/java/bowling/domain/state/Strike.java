package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Strike extends Finished {

    private Pin current;

    public Strike(Pin current) {
        this.current = current;
    }

    @Override
    public State roll(int next) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Pin> toPins() {
        return Arrays.asList(current);
    }

    @Override
    public Score getScore() {
        return Score.ofStrike(current.getCount());
    }

    @Override
    public String toString() {
        return "Strike{" +
                "current=" + current +
                '}';
    }
}
