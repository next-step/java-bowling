package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

public class Spare extends State {

    private final Pitch firstPitch;
    private final Pitch secondPitch;

    public Spare(Pitch firstPitch, int pins) {
        this.firstPitch = firstPitch;
        this.secondPitch = this.firstPitch.next(pins);
    }

    @Override
    public State bonusBowling(int pins) {
        return new Bonus(this, Ready.of(pins));
    }

    @Override
    public State bowling(int pins) {
        return Ready.of(pins);
    }

    @Override
    public String symbol() {
        return String.format("%s|/", firstPitch.pins());
    }

    @Override
    public Score score() {
        return null;
    }
}
