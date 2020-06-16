package bowling.step2.domain.scores;

import bowling.step2.domain.Score;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class FinalScores extends Scores {
    private final Score bonusScore;

    private FinalScores(Score firstScore, Score secondScore, Score bonusScore) {
        super(firstScore, secondScore);
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
    public boolean isFullOf() {
        return firstScore != null && secondScore != null && bonusScore != null;
    }

    @Override
    public Stream<Score> stream() {
        return Stream.of(firstScore, secondScore, bonusScore);
    }
}