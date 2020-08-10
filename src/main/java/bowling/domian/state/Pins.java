package bowling.domian.state;

public class Pins {
    public static Pins bowl(int pinCount) {
        return new Pins();
    }

    public boolean isStrike() {
        return false;
    }

    public Pins secondBowl(int pinCount) {
        return new Pins();
    }

    public boolean isSpare(Pins secondPins) {
        return false;
    }

    public boolean isMiss(Pins secondPins) {
        return false;
    }
}
