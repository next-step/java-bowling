package bowling.step4.domain.scores;

import bowling.step4.domain.Score;
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