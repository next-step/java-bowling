package bowling.domain.pitch;

class Ready implements State {
    public static State bowlFirst(Pin pin) {
        if (pin.isMax()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }

    @Override public State bowl(Pin pin) {
        return bowlFirst(pin);
    }
}
