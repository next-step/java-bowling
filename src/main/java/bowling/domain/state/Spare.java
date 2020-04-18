package bowling.domain.state;

import bowling.domain.score.Score;

public class Spare extends Finished {
    private static final String TEXT = "/";

    private PinCount lastPlayPoint;
    private PinCount currentPlayPoint;

    public Spare(PinCount felledPin, PinCount newFelledPin) {
        this.lastPlayPoint = felledPin;
        this.currentPlayPoint = newFelledPin;
    }

    @Override
    public State play(PinCount newFelledPin) {
        if (newFelledPin.isMaxPinCount()) {
            return new Strike();
        }
        if (newFelledPin.isMinPinCount()) {
            return new Gutter();
        }

        return new End(newFelledPin);
    }

    @Override
    public String getString() {
        return TEXT;
    }

    @Override
    public int getFelledPin() {
        return currentPlayPoint.getValue();
    }

    @Override
    public Score createScore() {
        return new Score(10, 1);
    }

}
