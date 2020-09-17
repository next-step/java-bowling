package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.State;

import java.util.Arrays;
import java.util.List;

public class Miss extends Finished {

    private Pin current;
    private Pin next;

    public Miss(Pin current, int count) {
        this.current = current;
        this.next = Pin.of(count);
    }

    @Override
    public State roll(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Pin> toPins() {
        System.out.println(current);
        System.out.println(next);
        return Arrays.asList(current, next);
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(current.getCount() + next.getCount());
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
        return "Miss{" +
                "current=" + current +
                ", next=" + next +
                '}';
    }
}