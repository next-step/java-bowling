package bowling.domain.score;

import bowling.domain.frame.FinalFrame;

public abstract class AbstractFinalScore implements Score {

    public static final int NAN = -1;

    protected final FinalFrame finalFrame;
    protected final int score;
    protected final boolean valid;

    public AbstractFinalScore(FinalFrame finalFrame) {
        this.finalFrame = finalFrame;
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
