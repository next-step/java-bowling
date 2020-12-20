package bowling.domain;

public class FirstBowl implements Bowl {

    public static final int PINS_ZERO = 0;

    @Override
    public State stroke(Pins pins) {
        Score score = new Score(pins);

        if (score.isStrike()) {
            return new Strike();
        }
        if (score.isGutter()) {
            return new Gutter();
        }
        return new Miss(pins, new Pins(PINS_ZERO));
    }
}
