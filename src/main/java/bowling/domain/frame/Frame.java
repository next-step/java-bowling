package bowling.domain.frame;

import bowling.domain.shot.ShotScore;
import bowling.domain.frame.score.FrameScore;

import java.util.List;

public interface Frame {
    Frame last();

    Frame next();

    boolean isFrameSet();

    List<ShotScore> shotScores();

    FrameScore getFrameScore();

    void shot(int shot);
}
