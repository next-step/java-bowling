package bowling.domain.frame;

import bowling.domain.Content;
import bowling.exception.NotCreateFrameException;

public interface Frame {

    Frame next() throws NotCreateFrameException;
    void bowling(int hit);
    boolean isFinish();
    boolean hasLastBonusChance();
    Content content();
}
