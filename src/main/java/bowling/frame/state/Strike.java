package bowling.frame.state;

import bowling.score.Pin;

public class Strike extends Done {

    private final Pin currentPins;

    private Strike(Pin currentPins) {
        this.currentPins = currentPins;
    }

    public static Strike of(Pin currentPins) {
        return new Strike(currentPins);
    }

    @Override
    public State bowl(String falledPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "X";
    }
}
