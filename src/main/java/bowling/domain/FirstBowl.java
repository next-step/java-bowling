package bowling.domain;

public class FirstBowl implements Bowl {
    @Override
    public State stroke(Pins pins) {
        Score score = new Score(pins);
        if (score.isStrike()) {
            return new Strike();
        }
        if (score.isGutter()) {
            return new Gutter(pins);
        }
        return new Miss(pins);
    }
}
