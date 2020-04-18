package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.Constants.MAX_FELLED_PIN_COUNT;

public class Strike extends Finished {
    private static final String TEXT = "X";

    @Override
    public State play(PinCount newFelledPin) {
        if (newFelledPin.isMaxPinCount()) {
            return new Strike();
        }
        if (newFelledPin.isMinPinCount()) {
            return new Gutter();
        }

        return new Playing(newFelledPin);
    }

    @Override
    public String getString() {
        return TEXT;
    }

    @Override
    public int getFelledPin() {
        return MAX_FELLED_PIN_COUNT;
    }


    @Override
    public Score createScore() {
        return new Score(10, 2);
    }
}
