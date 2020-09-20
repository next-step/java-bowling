package bowling.domain.score;

import bowling.domain.frame.NormalFrame;

public abstract class AbstractNormalScore implements Score {

    public static final int NAN = -1;

    protected final NormalFrame normalFrame;
    protected final int score;
    protected final boolean valid;

    public AbstractNormalScore(NormalFrame normalFrame) {
        this.normalFrame = normalFrame;
        this.valid = checkValid();
        this.score = this.valid ? calculateScore() : NAN;
    }

    protected abstract int calculateScore();

    public int getScore() {
        return score;
    }

    public boolean isValid() {
        return valid;
    }

}
