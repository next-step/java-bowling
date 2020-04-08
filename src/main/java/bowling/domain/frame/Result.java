package bowling.domain.frame;

public class Result {
    private String display;
    private int score;

    public Result(String display, int score) {
        this.display = display;
        this.score = score;
    }

    public int totalScore(int beforeScore) {
        if (this.score == -1) {
            return this.score;
        }
        return beforeScore + score;
    }

    public boolean isCalculation() {
        return this.score != -1;
    }

    @Override
    public String toString() {
        return "Result{" +
                "display='" + display + '\'' +
                ", score=" + score +
                '}';
    }
}
