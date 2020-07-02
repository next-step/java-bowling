package bowling.domain.status.finished;

import bowling.domain.status.Finished;

public class Miss extends Finished {
    int firstPin;
    int downPin;

    public Miss(int firstPin) {
        this.firstPin = firstPin;
    }

    public Miss(int firstPin, int downPin) {
        this.firstPin = firstPin;
        this.downPin = downPin;
    }

    @Override
    public String printResult() {
        return isGutter(firstPin) + "|" + isGutter(downPin);
    }

    @Override
    public boolean isClearAllPins() {
        return false;
    }

}
