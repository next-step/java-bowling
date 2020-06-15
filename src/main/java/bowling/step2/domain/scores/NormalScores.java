package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

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

    public static boolean isSparedOf(List<Score> scores) {
        if (scores.get(0) == Score.getStrike()) {
            return false;
        }
        return of(scores.get(0), scores.get(1)).isSpared();
    }

    @Override
    public Scores nextInit(Score score) {
        if (firstScore == null) {
            return of(score, null);
        }
        return of(firstScore, score);
    }
}