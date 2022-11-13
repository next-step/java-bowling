package bowling.domain.score;

import bowling.domain.score.scores.BonusScores;
import bowling.domain.score.scores.DefaultFrameScores;
import bowling.domain.score.scores.LastFrameScores;
import bowling.domain.score.scores.Scores;
import java.util.Objects;

public class TotalScore {
    private final Scores regularScores;
    private final Scores bonusScores;

    public static TotalScore defaultFrameTotalScore() {
        return new TotalScore(new DefaultFrameScores(), new BonusScores());
    }

    public static TotalScore lastFrameTotalScore() {
        return new TotalScore(new LastFrameScores(), new BonusScores());
    }

    public TotalScore(Scores regularScores, Scores bonusScores) {
        this.regularScores = regularScores;
        this.bonusScores = bonusScores;
    }

    public void addRegularScore(Score score) {
        this.regularScores.add(score);
    }

    public void addBonusScore(Score score) {
        this.bonusScores.add(score);
    }

    public int sumTotalScore() {
        return this.regularScores.sum() + this.bonusScores.sum();
    }

    public Scores regularScores() {
        return this.regularScores;
    }

    public void validateScore() {
        this.regularScores.validateScore();
    }

    public boolean isNotEndScoreAggregation() {
        return this.regularScores.isNotEndScore(this.regularScores)
                || this.bonusScores.isNotEndScore(this.regularScores);
    }

    public boolean isChanceMinusTwo() {
        return this.regularScores.isChanceMinusTwo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TotalScore that = (TotalScore) o;
        return Objects.equals(this.regularScores, that.regularScores) && Objects.equals(this.bonusScores,
                that.bonusScores);
    }
}
