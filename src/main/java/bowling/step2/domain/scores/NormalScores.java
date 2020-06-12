package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

public class NormalScores implements Scores {
    private final Score firstScore;
    private final Score secondScore;

    private NormalScores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Scores of (Score firstScore, Score secondScore) {
        return new NormalScores(firstScore, secondScore);
    }

    public static Scores init () {
        return of(null, null);
    }

    @Override
    public Scores firstInit (Score score) {
        return of(score, null);
    }

    @Override
    public Scores secondInit (Score score) {
        return of(firstScore, score);
    }

    @Override
    public boolean isStrike () {
        return firstScore == Score.getStrike();
    }

    @Override
    public boolean isSpared () {
        return firstScore.sum(secondScore) == Score.getStrike();
    }

    @Override
    public int totalScore () {
        return firstScore.sum(secondScore).getValue();
    }
}