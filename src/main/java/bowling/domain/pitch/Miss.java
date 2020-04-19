package bowling.domain.pitch;

class Miss implements State {
    @Override public State bowl(Pin pin) {
        return Ready.bowlFirst(pin);
    }
}
