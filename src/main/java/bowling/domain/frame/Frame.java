package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;

public abstract class Frame {

    public static final int SCORE_STRIKE = 10;
    protected final TotalScore totalScore;
    protected final Chance chance;

    public Frame() {
        this.totalScore = new TotalScore();
        this.chance = new Chance(this.TotalChance());
    }

    protected abstract int TotalChance();

    protected abstract void validateScore(Frame frame);

    public abstract void minusChance();

    public abstract boolean isNotEndScoreAggregation();

    public void addScore(Score score) {
        if (!this.chance.isRemainChance()) {
            throw new IllegalArgumentException("남은 기회가 없습니다.");
        }
        this.totalScore.addRegularScore(score);
        validateScore(this);
        this.minusChance();
    }

    public boolean isRemainChance() {
        return this.chance.isRemainChance();
    }

    public TotalScore totalScore() {
        return this.totalScore;
    }

    public void addBonusScore(Score score) {
        this.totalScore.addBonusScore(score);
    }
}
