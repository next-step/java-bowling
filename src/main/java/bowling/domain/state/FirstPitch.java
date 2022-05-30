package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

public class FirstPitch extends State {

    private final Pitch firstPitch;

    public FirstPitch(int pins) {
        this.firstPitch = Pitch.of(pins);
    }

    @Override
    public State bowling(int pins) {
        int totalPins = Math.addExact(firstPitch.pins(), pins);
        if (totalPins == 10) {
            return new Spare(this.firstPitch, pins);
        }

        if (totalPins == 0) {
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
