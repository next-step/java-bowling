package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class NormalScores implements Scores {
    private final Score firstScore;
    private final Score secondScore;

    private NormalScores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static NormalScores of (Score firstScore, Score secondScore) {
        return new NormalScores(firstScore, secondScore);
    }

    public static NormalScores init () {
        return of(null, null);
    }

    public static boolean isSparedOf (List<Score> scores) {
        if (scores.get(0) == Score.getStrike()) {
            return false;
        }
        return of(scores.get(0), scores.get(1)).isSpared();
    }

    @Override
    public Scores nextInit (Score score) {
        if (firstScore == null) {
            return of(score, null);
        }
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
    public boolean isEmptyOf (int index) {
        if (index > 1) {
            return false;
        }
        return asList(firstScore, secondScore).get(index) == null;
    }

    @Override
    public boolean isFullOf () {
        return firstScore != null && secondScore != null;
    }

    @Override
    public int totalScore () {
        return firstScore.sum(secondScore).getValue();
    }

    @Override
    public Stream<Score> stream () {
        return Stream.of(firstScore, secondScore);
    }
}