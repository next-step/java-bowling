package bowling;

public class Frame {
    private int pinCount = 10;
    private int attemptCount;

    public Frame(int count) {
        throwBall(count);
    }

    public boolean isStrike() {
        return this.attemptCount == 1 && this.pinCount == 0;
    }

    private boolean isSpare() {
        return this.attemptCount == 2 && this.pinCount == 0;
    }

    private boolean isMiss() {
        return attemptCount == 2 && this.pinCount > 0;
    }

    public void throwBall(int count) {
        this.pinCount -= count;

        if (this.pinCount < 0) {
            throw new IllegalArgumentException("핀은 10개 이상 넘어갈 수 없습니다");
        }
        attemptCount++;
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
