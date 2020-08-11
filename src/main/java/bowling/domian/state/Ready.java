package bowling.domian.state;

public class Ready implements State {
    public State bowl(int falledPinsCount) {
        Pins pins = Pins.bowl(falledPinsCount);

        if (pins.isStrike()) {
            return new Strike();
        }

        return new FirstBowl();
    }

    public boolean isBowlPossible() {
        return true;
    }
}
