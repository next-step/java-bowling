package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.List;

public interface Frame {
    void addScore(int point);

    boolean isPlayable();

    boolean isCalculatableFrame(int frameIndex);

    List<Score> getScores();

    int getTotalPoint(int frameIndex);
}
