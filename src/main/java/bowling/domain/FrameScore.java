package bowling.domain;

import bowling.domain.scoreType.ScoreType;

class FrameScore {
    private int score;
    private int leftShotCount;

    private FrameScore(int score, int leftShotCount) {
        this.score = score;
        this.leftShotCount = leftShotCount;
    }

    static FrameScore of(Score score, ScoreType scoreType) {
        if (!scoreType.isFinished()) {
            throw new IllegalArgumentException(String.format("scoreType must be Finished Type : scoreType = %s", score));
        }
        return new FrameScore(score.score(), scoreType.getBonusCount());
    }

    int getScore() {
        if (!isScoreCalculated()) {
            throw new IllegalStateException("FrameScore not Calculated");
        }
        return score;
    }

    boolean isScoreCalculated() {
        return leftShotCount == 0;
    }

    void addBonus(int bonusShot) {
        if (leftShotCount == 0) {
            throw new IllegalStateException("Cannot addBonus");
        }
        score += bonusShot;
        leftShotCount--;
    }
}
