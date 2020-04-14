package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.List;

public interface Frame {
    void addScore(int score);

    boolean isPlayable();

    List<Score> getScores();

    String getTotalPoint(int frameIndex);
}
