package bowling.domain.state;

public class Ready extends NotFinished {

    private static final String TEXT = "";

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
