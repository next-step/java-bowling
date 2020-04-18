package bowling.domain.pitch;

class Spare implements State {
    @Override public State bowl(int pinCount) {
        return Ready.bowlFirst(pinCount);
    }
}
