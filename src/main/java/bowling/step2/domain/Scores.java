package bowling.step2.domain;

public class Scores {
    private final Score firstScore;
    private final Score secondScore;

    private Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Scores of (Score firstScore, Score secondScore) {
        return new Scores(firstScore, secondScore);
    }

    public static Scores ofStrike () {
        return of(Score.getStrike(), null);
    }

    public boolean isStrike () {
        return firstScore == Score.getStrike();
    }

    public boolean isSpared () {
        return firstScore.sum(secondScore) == Score.getStrike();
    }

    public int totalScore () {
        return firstScore.sum(secondScore).getValue();
    }
}