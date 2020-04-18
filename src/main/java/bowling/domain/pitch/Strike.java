package bowling.domain.pitch;

class Strike implements State {
    @Override public State bowl(int pinCount) {
        return Ready.bowlFirst(pinCount);
    }
}
