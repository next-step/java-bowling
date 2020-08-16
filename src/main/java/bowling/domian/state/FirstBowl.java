package bowling.domian.state;

public class FirstBowl implements State {
    private final Pins firstPins;

    public FirstBowl(Pins pins) {
        this.firstPins = pins;
    }

    public State bowl(int falledPinsCount) {
        Pins secondPins = firstPins.secondBowl(falledPinsCount);

        if (secondPins.isSpare(firstPins)) {
            return new Spare();
        }

        return new Miss(firstPins, secondPins);
    }

    public boolean isFinished() {
        return false;
    }
}
