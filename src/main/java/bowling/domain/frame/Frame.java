package bowling.domain.frame;

import bowling.domain.status.FrameStatus;

public interface Frame {

    void bowl(int score);

    boolean isEndCondition(int score);

    boolean isEnd();

    FrameStatus getStatus();

}
