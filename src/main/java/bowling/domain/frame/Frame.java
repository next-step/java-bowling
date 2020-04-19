package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.scores.Scores;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    protected final Scores scores;

    public Frame(Scores scores) {
        this.scores = scores;
    }

    public List<Score> getScores() {
        return new ArrayList<>(scores.getScores());
    }

    public abstract void addScore(int point);

    public abstract boolean isPlayable();

    public abstract boolean isCalculatableFrame(int frameIndex);

    public abstract int getTotalPoint(int frameIndex);
}
