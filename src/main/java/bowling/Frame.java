package bowling;

public class Frame {
    private PinCount pinCount;
    private int attemptCount;

    public Frame() {
        pinCount = new PinCount();
    }

    public boolean isStrike() {
        return this.attemptCount == 1 && this.pinCount.isZero();
    }

    private boolean isSpare() {
        return this.attemptCount == 2 && this.pinCount.isZero();
    }

    public ScoreType throwBall(int count) {
        if (attemptCount > 2) {
            throw new IllegalArgumentException("두번 이상 투구를 할 수 없습니다.");
        }

        attemptCount++;
        this.pinCount.decreaseCount(count, isFirst());

        return getScoreType(count);
    }

    private ScoreType getScoreType(int count) {
        if (isStrike()) {
            return ScoreType.STRIKE;
        }

        if (isSpare()) {
            return ScoreType.SPARE;
        }

        if (count == 0) {
            return ScoreType.GUTTER;
        }

        return ScoreType.MISS;
    }

    private boolean isFirst() {
        return attemptCount == 1;
    }
}
