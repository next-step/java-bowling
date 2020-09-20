package bowling.domain.state;

import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;

public class Miss extends FinishedState {

    private final Pin first;
    private final Pin second;

    private Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public static State of(Pin firstPin, Pin felledPin) {
        return new Miss(firstPin, felledPin);
    }

    public Pin getFirstPin() {
        return this.first;
    }

    public Pin getSecondPin() {
        return this.second;
    }

    @Override
    public Score calculate(Score baseScore) {
        baseScore = baseScore.add(first.toScore());
        if (baseScore.isPending()) {
            baseScore = baseScore.add(second.toScore());
        }
        return baseScore;
    }

    @Override
    public Score getScore() {
        return first.add(second).toScore();
    }
}
