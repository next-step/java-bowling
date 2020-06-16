package bowling.step3.domain.scores;

import bowling.step3.domain.Score;
import bowling.step3.domain.ScoreType;

import java.util.List;
import java.util.stream.Stream;

public class NormalScores extends Scores {

    private NormalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
    }

    public static NormalScores of(Score firstScore, Score secondScore) {
        return new NormalScores(firstScore, secondScore);
    }

    public static NormalScores init() {
        return of(null, null);
    }

    public static boolean isType(List<Score> scores, ScoreType scoreType) {
        return !scores.get(0).isType(ScoreType.STRIKE) &&
               of(scores.get(0), scores.get(1)).isType(scoreType);
    }

    @Override
    public Stream<Score> stream() {
        return Stream.of(firstScore, secondScore);
    }

    @Override
    public Scores nextInit(Score score) {
        if (firstScore == null) {
            return of(score, null);
        }
        return of(firstScore, score);
    }
}