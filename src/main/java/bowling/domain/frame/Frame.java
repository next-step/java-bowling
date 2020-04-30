package bowling.domain.frame;

import bowling.domain.ShotScore;
import bowling.domain.frameScore.FrameScore;

import java.util.List;

public interface Frame {
    Frame last();

    Frame next();

    boolean isFrameSet();

    List<ShotScore> shotScores();

    FrameScore getFrameScore();

    void shot(int shot);
}
