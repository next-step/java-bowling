package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;

public abstract class Frame {
    protected final Chance chance;
    protected final TotalScore totalScore;

    public abstract boolean isNotEndScoreAggregation();

    public void minusChance() {
        if (this.totalScore.isChanceMinusTwo()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    protected Frame(Chance chance, TotalScore totalScore) {
        this.chance = chance;
        this.totalScore = totalScore;
    }

    public boolean isRemainChance() {
        return this.chance.isRemainChance();
    }

    public void addScore(Score score) {
        if (!this.chance.isRemainChance()) {
            throw new IllegalArgumentException("남은 기회가 없습니다.");
        }
        this.totalScore.addRegularScore(score);
        this.totalScore.validateScore();
        this.minusChance();
    }

    public void addBonusScore(Score score) {
        this.totalScore.addBonusScore(score);
    }

    public TotalScore totalScore() {
        return this.totalScore;
    }
}
