package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

public class Miss extends State {

    private final Pitch firstPitch;
    private final Pitch secondPitch;

    public Miss(Pitch firstPitch, int pins) {
        this.firstPitch = firstPitch;
        this.secondPitch = this.firstPitch.next(pins);
    }

    @Override
    public String symbol() {
        return String.format("%s|%s", this.firstPitch.pins(), this.secondPitch.pins());
    }

    @Override
    public int totalScore() {
        return Math.addExact(this.firstPitch.pins(), this.secondPitch.pins());
    }

    @Override
    public Score calculateScore(Score before) {
        Score after = before.nextScore(this.firstPitch.pins());

        if (after.doNotCalculate()) {
            return after;
        }

        return after.nextScore(this.secondPitch.pins());
    }

    @Override
    public State bowling(int pins) {
        return Ready.of(pins);
    }
}
