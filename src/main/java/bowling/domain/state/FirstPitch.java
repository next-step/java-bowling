package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

public class FirstPitch extends State {

    private static final int MAX_PINS = 10;

    private static final int GUTTER = 0;

    private final Pitch firstPitch;

    public FirstPitch(int pins) {
        this.firstPitch = Pitch.of(pins);
    }

    @Override
    public State bowling(int pins) {
        int totalPins = Math.addExact(firstPitch.pins(), pins);
        if (totalPins == MAX_PINS) {
            return new Spare(this.firstPitch, pins);
        }

        if (totalPins == GUTTER) {
            return new Gutter();
        }

        return new Miss(this.firstPitch, pins);
    }

    @Override
    public String symbol() {
        return String.valueOf(this.firstPitch.pins());
    }

    @Override
    public Score score() {
        return null;
    }
}
