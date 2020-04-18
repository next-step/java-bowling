package bowling.domain.pitch;

class Ready implements State {
    public static State bowlFirst(int pinCount) {
        if (pinCount == Pitch.MAX) {
            return new Strike();
        }

        return new FirstBowl(pinCount);
    }

    @Override public State bowl(int pinCount) {
        return bowlFirst(pinCount);
    }
}
