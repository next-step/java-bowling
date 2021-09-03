package bowling.domain;

public abstract class State {
    protected Pins firstPins;
    protected Pins secondPins;

    public abstract void bowl(Pins pins);

    public Pins getFirstPin() {
        return firstPins;
    }

    public Pins getSecondPin() {
        return secondPins;
    }

    public abstract boolean isFinish();
}
