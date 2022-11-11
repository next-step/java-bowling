package bowling.domain.score;

public class TotalScore {

    public static final int SCORE_STRIKE = 10;
    private final Scores regularScores;
    private final Scores bonusScores;

    public TotalScore() {
        this.regularScores = new Scores();
        this.bonusScores = new Scores();
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
