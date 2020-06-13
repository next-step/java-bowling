package bowling.step2.domain.scores;

import bowling.step2.domain.Score;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class FinalScores implements Scores {
    private final Score firstScore;
    private final Score secondScore;
    private final Score bonusScore;

    private FinalScores(Score firstScore, Score secondScore, Score bonusScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.bonusScore = bonusScore;
    }

    public static FinalScores of (Score firstScore, Score secondScore) {
        return new FinalScores(firstScore, secondScore, null);
    }

    public static FinalScores init () {
        return of(null, null);
    }

    @Override
    public Scores nextInit (Score score) {
        if (firstScore == null) {
            return of(score, null);
        }
        if (secondScore == null) {
            return of(firstScore, score);
        }
        return new FinalScores(firstScore, secondScore, score);
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
    public boolean isFullOf () {
        return firstScore != null && secondScore != null && bonusScore != null;
    }

    @Override
    public int totalScore () {
        return Stream.of(firstScore, secondScore, bonusScore)
                     .reduce(
                         Score.MIN_SCORE,
                         (total, score) -> total + (score == null ? Score.MIN_SCORE : score.getValue()),
                         Integer::sum
                     );
    }
    @Override
    public Stream<Score> stream () {
        return Stream.of(firstScore, secondScore, bonusScore);
    }
}