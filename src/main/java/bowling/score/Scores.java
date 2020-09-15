package bowling.score;

public class Scores {

    private Score firstScore;
    private Score secondScore;

    private Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Scores init() {
        return new Scores(null, null);
    }

    public boolean isDone() {
        return firstScore != null && secondScore != null;
    }

    public boolean isStrike() {
        return firstScore == Score.MAX;
    }

    public String getResult() {
        return "";
    }
}
