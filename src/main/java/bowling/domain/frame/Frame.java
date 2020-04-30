package bowling.domain.frame;

import bowling.domain.shot.Shot;
import bowling.domain.frame.score.FrameScore;

import java.util.List;

public interface Frame {
    Frame last();

    Frame next();

    boolean isFrameSet();

    List<Shot> shotScores();

    FrameScore getFrameScore();

    void shot(int shot);
}
