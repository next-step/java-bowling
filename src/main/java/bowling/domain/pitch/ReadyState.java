package bowling.domain.pitch;

class ReadyState implements State {
    public static State bowlFirst(Pin pin) {
        if (pin.isMax()) {
            return new StrikeState();
        }

        return new FirstBowlState(pin);
    }

    @Override public State bowl(Pin pin) {
        return bowlFirst(pin);
    }

    @Override public boolean isStrike() {
        return false;
    }

    @Override public boolean isSpare() {
        return false;
    }
}
