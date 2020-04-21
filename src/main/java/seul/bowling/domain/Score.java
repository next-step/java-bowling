package seul.bowling.domain;

import lombok.Getter;

public class Score {
    private static final int ZERO = 0;

    @Getter
    private int bonusScoreCount;
    @Getter
    private int score;

    private Score(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addBonusScore(int bonusScore) {
        this.score += bonusScore;
        this.bonusScoreCount--;
    }

    public void addBonusScoreCount(int bonusScoreCount) {
        this.bonusScoreCount += bonusScoreCount;
    }

    public void addCumulativeScore(int score) {
        this.score += score;
    }

    public boolean endAddBonusScore() {
        return bonusScoreCount <= ZERO;
    }

    public static Score of(int score) {
        return new Score(score);
    }
}
