package bowling.domain.frame;

import bowling.domain.framestatus.FrameStatus;

public interface Frame {
    void bowl(int countOfHit);

    FrameStatus getFrameStatus();

    int size();

    int getScore();
}
