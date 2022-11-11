package bowling.domain.score;

import java.util.Objects;

public class TotalScore {

    private final Scores scores;
    private final Scores bonusScores;

    public TotalScore() {
        this.scores = new Scores();
        this.bonusScores = new Scores();
    }

    public void addScore(Score score) {
        this.scores.add(score);
    }

    public void addBonusScore(Score score) {
        this.bonusScores.add(score);
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
        return Objects.equals(this.scores, that.scores) && Objects.equals(this.bonusScores, that.bonusScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.scores, this.bonusScores);
    }
}
