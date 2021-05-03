package bowling.domain;

public class Score {
    private int scoreTotal;
    private int sumAvailability;

    public Score(int baseScore, int sumAvailability) {
        scoreTotal = baseScore;
        this.sumAvailability = sumAvailability;
    }

    public boolean canCalculate() {
        if (sumAvailability > 0) {
            return false;
        }
        return true;
    }

    public void sum(Score score) {
        if(canCalculate()) {
            --sumAvailability;
            scoreTotal += score.scoreTotal;
        }
    }

    public int getScoreTotal() {
        return scoreTotal;
    }
}
