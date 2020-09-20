package bowling.domain.score;

import bowling.domain.frame.Frame;

public abstract class AbstractScore implements Score {

    public static final int NAN = -1;

    protected final Frame frame;
    protected final int score;
    protected final boolean valid;

    public AbstractScore(Frame frame) {
        this.frame = frame;
        this.valid = checkValid();
        this.score = this.valid ? calculateScore() : NAN;
    }

    protected abstract int calculateScore();
    protected abstract boolean checkValid();

    public int getScore() {
        return score;
    }

    public boolean isValid() {
        return valid;
    }

}
