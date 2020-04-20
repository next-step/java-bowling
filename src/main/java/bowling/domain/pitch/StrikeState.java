package bowling.domain.pitch;

class StrikeState implements State {
    @Override public State bowl(Pin pin) {
        return ReadyState.bowlFirst(pin);
    }
}
