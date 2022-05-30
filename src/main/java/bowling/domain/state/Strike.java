package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

public class Strike extends State {

    private final Pitch firstPitch;

    public Strike(int pins) {
        this.firstPitch = Pitch.of(pins);
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
        return "X";
    }

    @Override
    public Score score() {
        return null;
    }
}
