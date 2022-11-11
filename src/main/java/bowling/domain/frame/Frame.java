package bowling.domain.frame;

import bowling.domain.Chance;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import java.util.Objects;

public abstract class Frame {

    public static final int SCORE_STRIKE = 10;
    protected final Scores scores;
    protected final Scores bonusScores;
    protected final Chance chance;

    public Frame() {
        this.scores = new Scores();
        this.bonusScores = new Scores();
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
        this.scores.add(score);
        validateScore(this);
        this.minusChance();
    }

    public boolean isRemainChance() {
        return this.chance.isRemainChance();
    }

    public Scores scores() {
        return this.scores;
    }

    public void addBonusScore(Score score) {
        this.bonusScores.add(score);
    }

    public int totalScore() {
        return this.scores.sum() + this.bonusScores.sum();
    }

    public boolean isSpare() {
        return this.scores().isSizeOver(1)
                && Scores.sumScores(this.scores.first(), this.scores.second()) == SCORE_STRIKE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(this.scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores);
    }
}
