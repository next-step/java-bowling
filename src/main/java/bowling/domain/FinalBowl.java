package bowling.domain;

public class FinalBowl extends Bowl{

    private FinalBowl(Pins pins, States states) {
        super(pins, states);
    }

    public static FinalBowl init() {
        return new FinalBowl(Pins.init(), States.init());
    }

    @Override
    public FinalBowl bowl(Pin pin) {
        State last = getLastState();
        State next = last.bowl(getLastPin(), pin);
        if( last == State.READY) {
            states.removeLast();
        }
        states.add(next);
        pins.add(pin);
        if (next.isEnd()) {
            states.add(State.READY);
        }
        return this;
    }
}
