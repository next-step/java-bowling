package bowling.domain.score;

public class Score {
    private int score;
    private int additionalCount;

    public Score(int score, int additionalCount) {
        this.score = score;
        this.additionalCount = additionalCount;
    }

    public boolean canCalculateScore() {
        return additionalCount > 0;
    }

    public void addAdditionalScore(int felledPin) {
        score = score + felledPin;
        additionalCount--;
    }

    public int getScore() {
        return score;
    }
}
