package bowling.domain.score;

import bowling.domain.score.scores.BonusScores;
import bowling.domain.score.scores.DefaultFrameScores;
import bowling.domain.score.scores.LastFrameScores;
import bowling.domain.score.scores.Scores;

public class TotalScore {

    public static final int SCORE_STRIKE = 10;
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

    public Scores bonusScores() {
        return this.bonusScores;
    }

    public boolean isSpare() {
        return this.regularScores.isSizeOver(1)
                && Scores.sumScores(this.regularScores.first(), this.regularScores.second()) == SCORE_STRIKE;
    }
}
