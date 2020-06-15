package bowling.step3.domain.scores;

import bowling.step3.domain.Score;
import bowling.step3.domain.ScoreType;

import java.util.List;

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
        return of(scores.get(0), scores.get(1)).isType(ScoreType.SPARED);
    }

    @Override
    public Scores nextInit(Score score) {
        if (firstScore == null) {
            return of(score, null);
        }
        return of(firstScore, score);
    }
}