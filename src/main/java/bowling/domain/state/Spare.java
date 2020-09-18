package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Result;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Spare extends Finished {
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
    public List<Pin> toPins() {
        return Arrays.asList(current, next);
    }

    @Override
    public Score getScore() {
        return Score.ofSpare(current.getCount() + next.getCount());
    }

    @Override
    public Score sumScore(Score before) {
        if (before.canNextSum()) {
            before = Score.of(current.getCount(), 0).sum(before);
        }

        if (before.canNextSum()) {
            before = Score.of(next.getCount(), 0).sum(before);
        }

        return before;
    }

    @Override
    public String toString() {
        return "Spare{" +
                "current=" + current +
                ", next=" + next +
                '}';
    }
}
