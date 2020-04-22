package bowling.domain.pitch;

class StrikeState implements State {
    @Override public State bowl(Pin pin) {
        return ReadyState.bowlFirst(pin);
    }

    @Override public boolean isStrike() {
        return true;
    }

    @Override public boolean isSpare() {
        return false;
    }
}
