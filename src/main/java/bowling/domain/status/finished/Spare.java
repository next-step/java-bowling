package bowling.domain.status.finished;

import bowling.domain.result.Score;
import bowling.domain.status.Finished;

public class Spare extends Finished {
    int firstPin;
    int secondPin;

    public Spare(int firstPin, int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String printResult() {
        return isGutter(firstPin) + "|/";
    }

    @Override
    public boolean isClearAllPins() {
        return true;
    }

    @Override
    public Score getScore() {
        return new Score(10, 1);
    }

}
