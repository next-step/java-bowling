package bowling.domain.frame;

public class Result {
    private String display;
    private int score;
    private int totalScore = -1;

    public Result(String display, int score) {
        this.display = display;
        this.score = score;
    }

    public int totalScore(int beforeScore) {
        if (this.score == -1) {
            this.totalScore = this.score;
            return this.totalScore;
        }
        this.totalScore = beforeScore + score;
        return this.totalScore;
    }

    public boolean isCalculation() {
        return this.score != -1;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getScore() {
        return score;
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public String toString() {
        return "Result{" +
                "display='" + display + '\'' +
                ", score=" + score +
                '}';
    }
}
