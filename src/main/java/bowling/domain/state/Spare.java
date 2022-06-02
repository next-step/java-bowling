package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

public class Spare extends State {

    private static final int LEFT_COUNT = 1;

    private final Pitch firstPitch;
    private final Pitch secondPitch;

    public Spare(Pitch firstPitch, int pins) {
        this.firstPitch = firstPitch;
        this.secondPitch = this.firstPitch.next(pins);
    }

    @Override
    public String symbol() {
        return String.format("%s|/", firstPitch.pins());
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
    public Score createScore() {
        return new Score(LEFT_COUNT, this.totalScore());
    }

    @Override
    public State bowling(int pins) {
        return Ready.of(pins);
    }

    @Override
    public State bonusBowling(int pins) {
        return new Bonus(this, Ready.of(pins));
    }
}
