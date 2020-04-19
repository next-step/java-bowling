package bowling.domain.pitch;

class Spare implements State {
    @Override public State bowl(Pin pin) {
        return Ready.bowlFirst(pin);
    }
}
