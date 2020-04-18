package bowling.domain.pitch;

class FirstBowl implements State {
    private int pinCount;
    public FirstBowl(int pinCount) {
        this.pinCount = pinCount;
    }

    @Override public State bowl(int pinCount) {
        if((this.pinCount + pinCount) == Pitch.MAX) {
            return new Spare();
        }
        return new Miss();
    }
}
