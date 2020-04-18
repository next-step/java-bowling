package seul.bowling.domain;

import lombok.Getter;

public class Score {
    private static final int ZERO = 0;

    @Getter
    private int bonusScoreCount;
    @Getter
    private int score;

    public void addScore(int score, int bonusScoreCount) {
        if (bonusScoreCountIsEmpty()) {
            this.bonusScoreCount += bonusScoreCount;
        }

        this.score += score;
    }

    public void addBonusScore(int bonusScore) {
        this.score += bonusScore;
        this.bonusScoreCount--;
    }

    public void addCumulativeScore(int score) {
        this.score += score;
    }

    public boolean bonusScoreCountIsEmpty() {
        return bonusScoreCount <= ZERO;
    }
}
