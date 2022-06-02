package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;

public class Strike extends State {

    private static final int LEFT_COUNT = 2;

    private final Pitch firstPitch;

    public Strike(int pins) {
        this.firstPitch = Pitch.of(pins);
    }

    @Override
    public String symbol() {
        return "X";
    }

    @Override
    public int totalScore() {
        return this.firstPitch.pins();
    }

    @Override
    public Score calculateScore(Score before) {
        return before.nextScore(this.totalScore());
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
