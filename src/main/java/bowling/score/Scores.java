package bowling.score;

import bowling.domain.NormalFrame;

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

    public static Scores first(Score firstScore, Score secondScore) {
        if(secondScore == null) {

        }
        return new Scores(firstScore, null);
    }

    public boolean isDone() {
        return firstScore != null && secondScore != null;
    }

    public boolean isStrike() {
        return firstScore == Score.MAX;
    }
}
