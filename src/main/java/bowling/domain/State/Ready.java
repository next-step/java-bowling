package bowling.domain.State;

public class Ready extends State {
    @Override
    public State bowl(Pin pin) {
        if (pin.isTen()) {
            return new Strike(pin);
        }

        if (pin.isZero()) {
            return new Gutter();
        }

        return new First(pin);
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}
