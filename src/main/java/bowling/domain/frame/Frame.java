package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    private static final int ZERO_POINT = 0;
    private static final int STRIKE_POINT = 10;

    protected final Scores scores;

    public Frame(Scores scores) {
        this.scores = scores;
    }

    public abstract void addScore(int point);

    public abstract boolean isPlayable();

    public abstract boolean isCalculatableFrame(int frameIndex);

    public abstract int getTotalPoint(int frameIndex);

    public List<Score> getScores() {
        return new ArrayList<>(scores.getScores());
    }

    protected ScoreType getSpareOrGutterType(Scores scores, int point) {
        if (point == ZERO_POINT) {
            return ScoreType.GUTTER;
        }
        if (scores.isSecondPlay() && scores.currentPoint() + point == STRIKE_POINT) {
            return ScoreType.SPARE;
        }
        return ScoreType.MISS;
    }
}
