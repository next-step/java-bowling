package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Pins;
import bowling.domain.score.Score;

public abstract class Frame {

    private static final int NOT_YET_START = 0;
    private static final int FIRST_TRY = 1;
    private static final int BONUS_TRY = 3;

    protected Pins pins;
    protected Score score;

    public Frame() {
        this.pins = new Pins();
    }


    public void additionalScore(int score) {
        this.score.addAdditionalScore(score);
    }

    public boolean canCalculate() {
        return score.canCalculateScore();
    }

    public boolean isBonusTry() {
        return pins.tryCount() == BONUS_TRY;
    }

    public boolean isFirstTry() {
        return pins.tryCount() == FIRST_TRY;
    }

    public boolean isNotYetStart() {
        return pins.tryCount() == NOT_YET_START;
    }

    public boolean hasScore() {
        return score != null;
    }

    public Pins pins() {
        return pins;
    }

    public void throwBall(int hitCount) {
        validateTry();
        validateHitCount(hitCount);
        pins.add(new Pin(hitCount));
    }

    public int score() {
        return score.calculateScore();
    }

    public abstract void createScore();

    public abstract int getScore();

    public abstract boolean roundEnded();

    protected abstract void validateTry();

    protected abstract void validateHitCount(int hitCount);

}
