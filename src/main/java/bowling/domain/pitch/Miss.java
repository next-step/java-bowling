package bowling.domain.pitch;

class Miss implements State {
    @Override public State bowl(int pinCount) {
        return Ready.bowlFirst(pinCount);
    }
}
