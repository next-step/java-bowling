package bowling.domain.frame.score;

import bowling.domain.shot.type.ScoreType;

import java.util.Objects;

public class DefaultFrameScore implements FrameScore {
    private int score;
    private int leftShotCount;

    private DefaultFrameScore(int score, int leftShotCount) {
        this.score = score;
        this.leftShotCount = leftShotCount;
    }

    public static DefaultFrameScore of(int score, ScoreType scoreType) {
        if (!scoreType.isFinished()) {
            throw new IllegalArgumentException(String.format("scoreType must be Finished Type : scoreType = %s", score));
        }
        return new DefaultFrameScore(score, scoreType.getBonusCount());
    }

    public static DefaultFrameScore of(int score, int bonusCount) {
        return new DefaultFrameScore(score, bonusCount);
    }

    public final static FrameScore NULL = new FrameScore() {
        @Override
        public boolean isCalculated() {
            return false;
        }

        @Override
        public int getScore() {
            throw new IllegalStateException("this FrameScore is Null");
        }

        @Override
        public void addBonus(int shot) {
        }
    };

    public int getScore() {
        if (!isCalculated()) {
            throw new IllegalStateException("FrameScore not Calculated");
        }
        return score;
    }

    public boolean isCalculated() {
        return leftShotCount == 0;
    }

    public void addBonus(int bonusShot) {
        if (leftShotCount == 0) {
            throw new IllegalStateException("Cannot addBonus");
        }
        score += bonusShot;
        leftShotCount--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultFrameScore that = (DefaultFrameScore) o;
        return score == that.score &&
                leftShotCount == that.leftShotCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftShotCount);
    }
}
