package bowling.domain;

public abstract class Frame {

    private static final int NOT_YET_START = 0;

    protected Pins pins;
    protected Score score;

    public Frame() {
        this.pins = new Pins();
    }

    public boolean canCalculate() {
        return score.canCalculateScore();
    }

    public boolean isNotYetStart() {
        return pins.tryCount() == NOT_YET_START;
    }

    public boolean hasScore() {
        return score != null;
    }

    public void throwBall(int hitCount) {
        validateTry();
        validateHitCount(hitCount);
        pins.add(new Pin(hitCount));
    }

    public Pins pins() {
        return pins;
    }

    public abstract void createScore();

    public abstract int getScore();

    public abstract boolean roundEnded();

    protected abstract void validateTry();

    protected abstract void validateHitCount(int hitCount);

    public void additionalScore(int score) {
        this.score.throwBall(score);
    }

    public int score() {
        return score.calculateScore();
    }
}
