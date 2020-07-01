package bowling.domain.status;

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
    public boolean canRemovePendingStatue() {
        return true;
    }

}
