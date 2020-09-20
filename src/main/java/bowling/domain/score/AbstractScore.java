package bowling.domain.score;

import bowling.domain.frame.Frame;

public abstract class AbstractScore implements Score {

    protected final Frame frame;
    protected final int score;
    protected final boolean valid;

    public AbstractScore(int score) {
        this.frame = null;
        this.score = score;
        this.valid = true;
    }

    public AbstractScore(Frame frame) {
        this.frame = frame;
        this.score = calculateScore();
        this.valid = checkValid();
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
