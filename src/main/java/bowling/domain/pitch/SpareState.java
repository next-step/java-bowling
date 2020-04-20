package bowling.domain.pitch;

class SpareState implements State {
    @Override public State bowl(Pin pin) {
        return ReadyState.bowlFirst(pin);
    }
}
