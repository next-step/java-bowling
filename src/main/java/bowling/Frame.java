package bowling;

public class Frame {
    private PinCount pinCount;
    private int attemptCount;

    public Frame(int count) {
        pinCount = new PinCount();
        throwBall(count);
    }

    public boolean isStrike() {
        return this.attemptCount == 1 && this.pinCount.isZero();
    }

    private boolean isSpare() {
        return this.attemptCount == 2 && this.pinCount.isZero();
    }

    private boolean isMiss() {
        return attemptCount == 2 && !pinCount.isZero();
    }

    public void throwBall(int count) {
        attemptCount++;
        this.pinCount.decreaseCount(count, isFirst());
    }

    private boolean isFirst() {
        return attemptCount == 1;
    }

    public ScoreType getScoreType() {
        if (isStrike()) {
            return ScoreType.STRIKE;
        }

        if (isSpare()) {
            return ScoreType.SPARE;
        }

        if (isMiss()) {
            return ScoreType.MISS;
        }


        return null;
    }
}
