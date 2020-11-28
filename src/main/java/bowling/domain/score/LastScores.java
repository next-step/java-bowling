package bowling.domain.score;

import java.util.Collections;
import java.util.List;

import static bowling.domain.score.Score.MAX_SCORE;

public class LastScores extends Scores {
    private static final int MAX_TRY_COUNT_AT_LAST = 3;
    private static final int MAX_SCORES_AT_LAST = 30;

    private LastScores(List<Score> scores) {
        super(scores);
    }

    public static LastScores of(List<Score> scores) {
        return new LastScores(scores);
    }

    public static LastScores empty() {
        return new LastScores(Collections.emptyList());
    }

    @Override
    protected Scores addScore(int score) {
        return new LastScores(super.addScore(score).getScores());
    }

    @Override
    protected int getMaxScores() {
        return MAX_SCORES_AT_LAST;
    }

    @Override
    public boolean isFinished() {
        return (secondTried() && sum() < MAX_SCORE) || thirdTried();
    }

    private boolean thirdTried() {
        return tryCount == MAX_TRY_COUNT_AT_LAST;
    }

    @Override
    protected boolean needNextScores() {
        return false;
    }

}
