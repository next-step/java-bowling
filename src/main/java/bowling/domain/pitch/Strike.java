package bowling.domain.pitch;

class Strike implements State {
    @Override public State bowl(Pin pin) {
        return Ready.bowlFirst(pin);
    }
}
