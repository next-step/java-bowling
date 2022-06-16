package bowling.domain.frame;

import bowling.domain.FrameNo;
import bowling.domain.Score;
import bowling.exception.NotCreateFrameException;

public interface Frame {

    Frame next() throws NotCreateFrameException;
    void bowling(int hit);
    Score calculate(Score score);
    boolean isFinish();
    boolean isProgressing();
    FrameNo frameNo();
    Score score();
}
