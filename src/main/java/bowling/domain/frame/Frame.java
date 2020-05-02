package bowling.domain.frame;

import bowling.domain.frame.score.FrameScore;
import bowling.domain.shot.Shot;

import java.util.List;

public interface Frame {
    Frame next();

    boolean isFrameSet();

    List<Shot> shots();

    FrameScore getFrameScore();

    void shot(int shot);

    int getFrameNumber();

    int getShotsCount();

    FrameScore addBonus(FrameScore beforScore);
}
