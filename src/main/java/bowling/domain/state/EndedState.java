package bowling.domain.state;

public abstract class EndedState extends State {
    public EndedState(Pin pin) {
        super(pin);
    }

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
}
