package bowling.domain.score;

import bowling.domain.state.Pin;

import static bowling.view.OutputView.BLANK;
import static bowling.view.OutputView.NORMAL_STATE_FORMAT;

public class Score {
    private static final int STRIKE_LEFT = 2;
    private static final int SPARE_LEFT = 1;
    private static final int SCORABLE_LEFT = 0;
    private static final int UNSCORABLE_LEFT = 1;

    protected final int score;
    protected final int left;

    public Score(Pin pin, int left) {
        this(pin.score(), left);
    }

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(Pin pin) {
        return new Score(score + pin.score(), left - 1);
    }

    public boolean isNotAbleToScore() {
        return left > SCORABLE_LEFT;
    }

    public boolean canScore() {
        return left <= SCORABLE_LEFT;
    }

    public Score add(Score score) {
        return new Score(score.score + this.score, 0);
    }

    public static Score unScorable(Pin pin) {
        return new Score(pin, UNSCORABLE_LEFT);
    }

    public static Score unScorable() {
        return new Score(0, UNSCORABLE_LEFT);
    }

    public static Score scorable() {
        return new Score(0, SCORABLE_LEFT);
    }

    public static Score strike(Pin pin) {
        return new Score(pin, STRIKE_LEFT);
    }

    public static Score spare(Pin pin) {
        return new Score(pin, SPARE_LEFT);
    }

    @Override
    public String toString() {
        if (canScore()) {
            return String.format(NORMAL_STATE_FORMAT, score);
        }
        return BLANK;
    }
}
