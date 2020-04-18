package bowling.domain.state;

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
}
