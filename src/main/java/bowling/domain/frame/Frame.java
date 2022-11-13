package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;

public abstract class Frame {

    public static final int SCORE_STRIKE = 10;
    protected final Chance chance;
    protected final TotalScore totalScore;

    protected abstract void validateScore(Frame frame);

    public abstract void minusChance();

    public abstract boolean isNotEndScoreAggregation();

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
        validateScore(this);
        this.minusChance();
    }

    public void addBonusScore(Score score) {
        this.totalScore.addBonusScore(score);
    }

    public TotalScore totalScore() {
        return this.totalScore;
    }
}
