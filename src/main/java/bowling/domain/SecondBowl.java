package bowling.domain;

public class SecondBowl implements Bowl {
    private Score score;

    public SecondBowl(Score score) {
        this.score = score;
    }

    @Override
    public State stroke(Pins pins) {
        if (score.isSpare(pins)) {
            return new Spare();
        }
        if (score.isGutter()) {
            return new Gutter();
        }
        return new Miss(this.score.getFirst(), pins);
    }
}
