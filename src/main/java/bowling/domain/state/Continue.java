package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;

public class Continue implements State {

    private final Pin first;

    private Continue(Pin first) {
        this.first = first;
    }

    public static State of(Pin felledPin) {
        return new Continue(felledPin);
    }

    @Override
    public State bowl(Pin felledPin) {
        Pin sum = first.add(felledPin);
        if (sum.isAllFelled()) {
            return Spare.of(first);
        }
        if (sum.isNotFelled()) {
            return Gutter.of();
        }
        return Miss.of(first, felledPin);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Score calculate(Score baseScore) {
        return baseScore.add(first.toScore());
    }

    @Override
    public Score getScore() {
        return first.toScore(Remaining.CONTINUE);
    }

    public Pin getFirstPin() {
        return this.first;
    }
}
