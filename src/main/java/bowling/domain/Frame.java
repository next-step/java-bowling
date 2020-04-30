package bowling.domain;

import bowling.domain.frameScore.FrameScore;

import java.util.List;

public interface Frame {
    Frame last();

    Frame next();

    boolean isFrameSet();

    List<ShotScore> shotScores();

    FrameScore getFrameScore();

    boolean isScoreCalculated();

    void shot(int shot);
}
