package bowling.domain;

public class SecondBowl implements Bowl {
    private final Score score;

    public SecondBowl(Score score) {
        this.score = score;
    }

    @Override
    public State stroke(Pins pins) {
        if (score.isSpare(pins)) {
            return new Spare(this.score.getFirst(), pins);
        }
        if (score.isAllGutter()) {
            return new Gutter(pins, pins);
        }
        return new Miss(this.score.getFirst(), pins);
    }
}
